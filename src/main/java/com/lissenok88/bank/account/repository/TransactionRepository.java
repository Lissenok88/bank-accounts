package com.lissenok88.bank.account.repository;

import com.lissenok88.bank.account.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
