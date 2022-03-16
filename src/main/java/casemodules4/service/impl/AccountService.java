package casemodules4.service.impl;

import casemodules4.model.Account;
import casemodules4.repository.IAccountRepository;
import casemodules4.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;
    @Override
    public Iterable<Account> findAllByUsername(String username) {
        return accountRepository.findAllByUsernameContaining(username);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> findByUsername(String name) {
        return accountRepository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String name) {
        return accountRepository.existsByUsername(name);
    }

    @Override
    public Boolean existsByEmail(String name) {
        return accountRepository.existsByEmail(name);
    }
}
