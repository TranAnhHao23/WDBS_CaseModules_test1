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
        return accountRepository.findAllByAccount(username);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> findByUsername(String name) {
        return accountRepository.findByAccount(name);
    }

    @Override
    public Boolean existsByUsername(String name) {
        return accountRepository.existsByAccount(name);
    }

    @Override
    public Boolean existsByEmail(String name) {
        return accountRepository.existsByEmail(name);
    }

    @Override
    public boolean checkLogin(Account account) {
        for (Account acc : accountRepository.findAll()) {
            if(acc.getEmail().equals(account.getEmail()) && acc.getPassword().equals(acc.getPassword())){
                return true;
            }
        }
        return false;
    }
}
