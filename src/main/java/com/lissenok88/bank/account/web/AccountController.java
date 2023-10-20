package com.lissenok88.bank.account.web;

import com.lissenok88.bank.account.model.Account;
import com.lissenok88.bank.account.model.AccountMapper;
import com.lissenok88.bank.account.repository.AccountRepository;
import com.lissenok88.bank.account.to.AccountTo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.lissenok88.bank.account.util.ValidationUtil.*;

@Slf4j
@RestController
@RequestMapping(value = AccountController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AccountController {
    public static final String REST_URL = "/api/account";
    private AccountRepository accountRepository;
    private AccountMapper accountMapper;

    @Autowired
    public AccountController(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Account> createWithLocation(@Valid @RequestBody AccountTo accountTo, @RequestParam String pin) {
        log.info("create account {}", accountTo);
        checkNew(accountTo);
        checkPin(pin);

        Account created = accountRepository.save(new Account(null, accountTo.getName(), 0L, pin));

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@Valid @RequestBody AccountTo accountTo, @PathVariable int id, @RequestParam String pin) {
        log.info("update account {} with id={}", accountTo, id);
        checkPin(pin);
        assureIdConsistent(accountTo, id);
        Account account = accountRepository.getExisted(id);
        equalPins(pin, account.getPin());
        accountRepository.save(accountMapper.toEntity(accountTo));
    }

    @GetMapping("/{id}")
    public AccountTo get(@PathVariable long id) {
        log.info("get account {}", id);
        return accountMapper.toTo(accountRepository.getExisted(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable long id) {
        log.info("delete by id={}", id);
        accountRepository.deleteExisted(id);
    }

    @GetMapping
    public List<AccountTo> getAll() {
        log.info("getAll");
        return accountMapper.toToList(accountRepository.findAll());
    }
}
