package com.store.app.service;

import com.store.app.entity.Author;
import com.store.app.entity.Post;
import com.store.app.entity.User;
import com.store.app.repository.AuthorRepository;
import com.store.app.utils.Constants;
import com.store.app.utils.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    private UserService userService;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, UserService userService) {
        this.authorRepository = authorRepository;
        this.userService = userService;
    }

    public ResponseEntity getAuthors() {
        List<Author> authors = authorRepository.findAllByOrderByFirstName();
        if (authors.isEmpty()) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.NOT_FOUND, Constants.NOT_FOUND, null);
        }
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.OK, Constants.FOUND, authors);
    }

    public ResponseEntity createAuthor(Author author) {
        // TO DO: Validations

        Author newAuthor = authorRepository.save(author);
        if (newAuthor == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.BAD_REQUEST, Constants.NOT_CREATED, null);
        }
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.CREATED, Constants.CREATED, author);
    }

    public ResponseEntity deleteAuthor(UUID id) {
        if (id == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.BAD_REQUEST, Constants.NOT_FOUND, null);
        }

        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.NOT_FOUND, Constants.NOT_FOUND, null);
        }

        authorRepository.deleteById(id);
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.OK, Constants.DELETED, null);
    }

    public User subscribe(User subscriber) {
        return this.authorRepository.saveSubscribedUser(subscriber);
    }

    public void unsubscribe(User subscriber) {
        this.authorRepository.deleteSubscribedUserByUuid(subscriber.uuid);
    }

    public void notifySubscribers(Post newPublishedPost) {
        Iterable<User> subscribedUsers = this.authorRepository.findAllSubscribedUsersByOrderByFirstName();
        for(User user : subscribedUsers) {
            this.userService.update(user, newPublishedPost);
        }
    }
}
