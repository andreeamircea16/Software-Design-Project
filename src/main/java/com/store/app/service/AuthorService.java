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

    /**
     * get all authors
     * @return an object which contains the request Http status, a success/error message and
     * a list of all authors ordered by their first name in case of success
     */
    public ResponseEntity getAuthors() {
        List<Author> authors = authorRepository.findAllByOrderByFirstName();
        if (authors.isEmpty()) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.NOT_FOUND, Constants.NOT_FOUND, null);
        }
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.OK, Constants.FOUND, authors);
    }

    /**
     * create an author
     * @param author the new author to be created
     * @return an object which contains the request Http status, a success/error message and
     * the author object created in case of success
     */
    public ResponseEntity createAuthor(Author author) {
        // TODO: Validations

        Author newAuthor = authorRepository.save(author);
        if (newAuthor == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.BAD_REQUEST, Constants.NOT_CREATED, null);
        }
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.CREATED, Constants.CREATED, author);
    }

    /**
     * delete an author by id
     * @param id the id of the author to be deleted
     * @return an object which contains the request Http status, a success/error message
     */
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

    /**
     * find the author to follow by id, add the subscribed user to subscribedUsers list and
     * updates the author
     * @param subscriber the user that will be subscribed
     * @param authorId the id of the author to follow
     * @return an object which contains the request Http status, a success/error message
     */
    public ResponseEntity subscribe(User subscriber, UUID authorId) {
        if (authorId == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.BAD_REQUEST, Constants.NOT_FOUND, null);
        }

        Author author = authorRepository.findById(authorId).orElse(null);
        if (author == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.NOT_FOUND, Constants.NOT_FOUND, null);
        }

        Author newAuthor = addSubscriber(author, subscriber);
        authorRepository.save(newAuthor);
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.OK, Constants.MODIFIED, null);
    }

    public void unsubscribe(User subscriber) {
        authorRepository.deleteSubscribedUserByUuid(subscriber.uuid);
    }

    /**
     * notify all subscribes users about the new post published by the author they follow
     * @param newPublishedPost the new post
     */
    public void notifySubscribers(Post newPublishedPost) {
        Iterable<User> subscribedUsers = this.authorRepository.findAllSubscribedUsersByOrderByFirstName();
        for(User user : subscribedUsers) {
            this.userService.update(user, newPublishedPost);
        }
    }

    /**
     * get the list of subscribedUsers, add the new subscriber and update the list
     * @param author the author to follow
     * @param subscriber the user that will be subscribed
     * @return the updated author object
     */
    private Author addSubscriber(Author author, User subscriber) {
        List<User> subscribers = author.getSubscribedUsers();
        // TODO: verify if already exists
        subscribers.add(subscriber);
        author.setSubscribedUsers(subscribers);
        return author;
    }
}
