package com.langthang.security;

import com.langthang.model.Account;
import com.langthang.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accRepo;

    @Autowired
    public UserDetailsServiceImpl(AccountRepository accRepo) {
        this.accRepo = accRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account acc = accRepo.findAccountByEmail(email);

        if (acc == null) {
            throw new UsernameNotFoundException("Account with " + email + " not found!");
        }

        return new User(acc.getEmail()
                , acc.getPassword()
                , acc.isEnabled()
                , true
                , true
                , true,
                Collections.singleton(acc.getRole()));
    }
}