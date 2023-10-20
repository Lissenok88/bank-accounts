package com.lissenok88.bank.account.web;

import com.lissenok88.bank.account.model.Account;
import com.lissenok88.bank.account.model.Transaction;
import com.lissenok88.bank.account.repository.AccountRepository;
import com.lissenok88.bank.account.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.lissenok88.bank.account.util.ValidationUtil.checkPin;
import static com.lissenok88.bank.account.util.ValidationUtil.equalPins;

@Slf4j
@RestController
@RequestMapping(value = TransactionController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TransactionController {

    public static final String REST_URL = "/api/transaction";
    public static final String DEPOSIT_OPERATION = "deposit account";
    public static final String WITHDRAW_OPERATION = "withdraw account";
    public static final String TRANSFER_OPERATION = "transfer to ";
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    private ResponseEntity<Transaction> create(Transaction transaction) {
        Transaction created = transactionRepository.save(transaction);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PatchMapping(value = "/deposit")
    @Transactional
    public void deposit(@RequestParam int amount, @RequestParam int idAccount, @RequestParam String pin) {
        log.info("deposit id={} on sum ={}", idAccount, amount);
        checkPin(pin);
        Account account = accountRepository.getExisted(idAccount);
        equalPins(pin, account.getPin());
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        create(new Transaction(null, idAccount, amount, DEPOSIT_OPERATION));
    }

    @PatchMapping(value = "/withdraw")
    @Transactional
    public void withdraw(@RequestParam int amount, @RequestParam int idAccount, @RequestParam String pin) {
        log.info("withdraw id={} on sum ={}", idAccount, amount);
        checkPin(pin);
        Account account = accountRepository.getExisted(idAccount);
        equalPins(pin, account.getPin());
        long balance = account.getBalance() - amount;
        if(balance < 0 ) {
            throw new IllegalArgumentException("Insufficient funds in the account.");
        } else {
            account.setBalance(balance);
            accountRepository.save(account);
        }
        create(new Transaction(null, idAccount, amount, WITHDRAW_OPERATION));
    }

    @PatchMapping(value = "/transfer-to-other-account")
    @Transactional
    public  void transferToOtherAccount(@RequestParam int amount, @RequestParam int idAccount, @RequestParam String pin, @RequestParam int idOtherAccount) {
        log.info("transfer between id={} and id other account={} on sum ={}", idAccount, idOtherAccount, amount);
        checkPin(pin);
        Account account = accountRepository.getExisted(idAccount);
        equalPins(pin, account.getPin());
        Account accountOther = accountRepository.getExisted(idOtherAccount);
        long balance = account.getBalance() - amount;
        if(balance < 0 ) {
            throw new IllegalArgumentException("Insufficient funds in the account.");
        }
        account.setBalance(account.getBalance() - amount);
        accountOther.setBalance(accountOther.getBalance() + amount);
        accountRepository.save(account);
        accountRepository.save(accountOther);

        create(new Transaction(null, idAccount, amount, TRANSFER_OPERATION + idOtherAccount));
    }
}
