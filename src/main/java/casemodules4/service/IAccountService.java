package casemodules4.service;

import casemodules4.model.Account;

import java.util.Optional;

public interface IAccountService {
    Iterable<Account> findAllByUsername(String username);
    void save(Account account);
    Optional<Account> findByUsername(String name);

    Boolean existsByUsername(String name);
    Boolean existsByEmail(String name);

    boolean checkLogin(Account account);

}
