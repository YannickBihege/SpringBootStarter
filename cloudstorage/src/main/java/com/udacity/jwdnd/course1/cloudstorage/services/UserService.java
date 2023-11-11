package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.form.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * User Service that performs business logic operations regarding users
 * This class needs to be a component, because our Controller has a reference to
 * it that it doesn't create itself. Marking this as a @Service lets Spring know
 * to make instances of this bean available to other classes, though @Component would work as well.
 */
@Service
public class UserService {

    public final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUserByUsername(username) == null;
    }

    public User createUser(String firstName, String lastName, String userName, String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(password, encodedSalt);
        return userMapper.insert(new User(null, userName, encodedSalt, hashedPassword, firstName, lastName));
    }

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public void deleteUser(Integer userId){
            userMapper.deleteUser(userId);
    };

    public boolean authenticate(String username, String password) {
        User user = userMapper.getUserByUsername(username);

        if (user != null) {
            String hashedPassword = hashService.getHashedValue(password, user.getSalt());
            return hashedPassword.equals(user.getPassword());
        }
        return false;
    }


}
