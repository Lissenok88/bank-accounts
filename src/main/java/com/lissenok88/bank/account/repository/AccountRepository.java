package com.lissenok88.bank.account.repository;

import com.lissenok88.bank.account.error.NotFoundException;
import com.lissenok88.bank.account.model.Account;
import com.lissenok88.bank.account.to.AccountTo;
import com.lissenok88.bank.account.util.Util;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.id=:id")
    int delete(long id);

    @Query("SELECT a FROM Account a")
    List<Account> getAll();

    //  https://stackoverflow.com/a/60695301/548473 (existed delete code 204, not existed: 404)
    default void deleteExisted(long id) {
        if (delete(id) == 0) {
            throw new NotFoundException("Entity with id=" + id + " not found");
        }
    }

    default Account getExisted(long id) {
        return Util.checkExist(id, findById(id));
    }
}
