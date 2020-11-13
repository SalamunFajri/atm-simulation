package com.sf.service.impl;

import com.sf.dao.impl.AccountRepository;
import com.sf.model.Account;
import com.sf.model.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Account> user = accountRepository.findByAccountNumber(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        MyUserDetails myUserDetails = new MyUserDetails(user.get());

        return new org.springframework.security.core.userdetails.User(myUserDetails.getUsername(), myUserDetails.getPassword(), grantedAuthorities);
    }
}
