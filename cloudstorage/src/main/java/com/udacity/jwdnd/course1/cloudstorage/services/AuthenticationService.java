package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider
{
    /**
     The class is provided by the nanodegree course.
     However it needs to be adapted in order to
     decrypt the given value
     String encryptedPassword = encryptionService.encryptValue(signupInput.getPassword(),encryptionKey,encodedSalt);

     */

    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final UserMapper userMapper;
    private final HashService hashService;
    private final EncryptionService encryptionService;

    private final static String encryptionKey = "key";


    public AuthenticationService(UserMapper userMapper, HashService hashService, EncryptionService encryptionService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
        this.encryptionService = encryptionService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userMapper.getUser(username); // Db
        if (user != null) {
            String encodedSalt = user.getSalt(); // Database
            String encryptedPassword = encryptionService.encryptValue(password,encryptionKey,encodedSalt);
            if (user.getPassword().equals(encryptedPassword)) {
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }
        return null;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}


