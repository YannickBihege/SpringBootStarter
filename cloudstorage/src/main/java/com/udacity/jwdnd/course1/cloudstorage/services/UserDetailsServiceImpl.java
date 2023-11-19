package com.udacity.jwdnd.course1.cloudstorage.services;

import com.sun.security.auth.UserPrincipal;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;

    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /*
    @Override
    public UserDetails loadUserByUsername2(String username) throws UsernameNotFoundException {
        User user = userMapper.getUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

     //   return new UserPrincipal(user);
    }
    */


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                // Add your logic to get authorities (roles) based on your application
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
