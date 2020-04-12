package com.store.app.service;

import com.store.app.entity.Author;
import com.store.app.entity.Post;
import com.store.app.entity.User;
import com.store.app.repository.UserRepository;
import com.store.app.utils.Constants;
import com.store.app.utils.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(UUID uuid) {
        return this.userRepository.findById(uuid).orElse(new User());
    }

    public List<User> getUsers() {
        return userRepository.findAllByOrderByFirstName();
    }

    /**
     * get the list of subscriptionPosts, add the new post, update the list and the user
     * @param user the new user to be created
     * @param newPublishedPost the new user to be created
     */
    public void update(User user, Post newPublishedPost) {
        List<Post> subscriptionPosts = user.getSubscriptionPosts();
        subscriptionPosts.add(newPublishedPost);
        user.setSubscriptionPosts(subscriptionPosts);
        this.userRepository.save(user);
    }

    /**
     * create a user
     * @param user the new user to be created
     * @return an object which contains the request Http status, a success/error message and
     * the user object created in case of success
     */
    public ResponseEntity createUser(User user) {
        // TODO: Validations

        User newUser = userRepository.save(user);
        if (newUser == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.BAD_REQUEST, Constants.NOT_CREATED, null);
        }
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.CREATED, Constants.CREATED, user);
    }
}
