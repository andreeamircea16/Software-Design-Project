package com.store.app.service;

import com.store.app.entity.User;
import com.store.app.repository.UserRepository;
import com.store.app.utils.Constants;
import com.store.app.utils.MessageHandler;
import com.store.app.utils.UserOperations;
import com.store.app.utils.UserTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserOperations {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity createAdminUser(User user) {
        User newAdminUser = userRepository.save(this.createUserByType(user, UserTypes.ADMIN));
        if (newAdminUser == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.BAD_REQUEST, Constants.NOT_CREATED, null);
        }
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.CREATED, Constants.CREATED, newAdminUser);
    }

    public ResponseEntity createUser(User user) {
        User newUser = userRepository.save(this.createUserByType(user, UserTypes.REGULAR));
        if (newUser == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.BAD_REQUEST, Constants.NOT_CREATED, null);
        }
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.CREATED, Constants.CREATED, newUser);
    }

    @Override
    public User createUserByType(User user, UserTypes type) {
        User newUser = user;
        if (type.equals(UserTypes.ADMIN)) {
            newUser.setIsAdmin(true);
        } else if (type.equals(UserTypes.REGULAR)) {
            newUser.setIsAdmin(false);
        } else {
            return null;
        }
        return newUser;
    }
}
