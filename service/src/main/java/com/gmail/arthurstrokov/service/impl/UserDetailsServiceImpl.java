package com.gmail.arthurstrokov.service.impl;

import com.gmail.arthurstrokov.dao.UserDao;
import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(
            @Qualifier("userDao") UserDao userDao
    ) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        User user = userDao.validateByEmail(email);
        UserPrincipal userPrincipal;
        if (user != null) {
            userPrincipal = new UserPrincipal(user);
        } else {
            throw new UsernameNotFoundException("User not found!");
        }
        return userPrincipal;
    }
}

