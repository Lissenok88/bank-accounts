package com.lissenok88.bank.account.model;

import com.lissenok88.bank.account.to.AccountTo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toEntity(AccountTo to);

    List<Account> toEntityList(Collection<AccountTo> tos);

    Account updateFromTo(AccountTo to, @MappingTarget Account entity);

    AccountTo toTo(Account entity);

    List<AccountTo> toToList(Collection<Account> entities);
}
