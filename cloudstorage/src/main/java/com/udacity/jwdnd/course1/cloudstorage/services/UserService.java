package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.security.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.security.HashService;
import org.springframework.stereotype.Service;


import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

/**
 * User Service that performs business logic operations regarding users
 * This class needs to be a component, because our Controller has a reference to
 * it that it doesn't create itself. Marking this as a @Service lets Spring know
 * to make instances of this bean available to other classes, though @Component would work as well.
 */
@Service
public class UserService {

    private final UserMapper userMapper;

    private final HashService hashService;
    private final EncryptionService encryptionService;
    private final static String encryptionKey = "key";



    public UserService(UserMapper userMapper, HashService hashService, EncryptionService encryptionService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
        this.encryptionService = encryptionService;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int saveUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String encryptedPassword = encryptionService.encryptValue(user.getPassword(),encryptionKey,encodedSalt);
        return userMapper.insert(new User(null, user.getUsername(), encodedSalt, encryptedPassword, user.getFirstName(), user.getLastName()));
    }


    public User getUserByUsername(String username) {
        return userMapper.getUser(username);
    }


    public List<User> findAllUsers() {
        return userMapper.findAll();
    }


    public void deleteUser(Integer userId) {
        userMapper.delete(userId);
    }



}


