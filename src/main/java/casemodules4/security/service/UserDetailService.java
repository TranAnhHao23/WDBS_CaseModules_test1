package casemodules4.security.service;

import casemodules4.model.Account;
import casemodules4.model.User;
import casemodules4.repository.IUserRepository;
import casemodules4.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username).orElseThrow( () -> new UsernameNotFoundException("User not found with username: " + username));
        return UserPrinciple.build(account);
    }
}