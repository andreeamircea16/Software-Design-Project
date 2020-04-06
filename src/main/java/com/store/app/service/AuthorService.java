package com.store.app.service;

import com.store.app.entity.Author;
import com.store.app.entity.Post;
import com.store.app.entity.User;
import com.store.app.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    private UserService userService;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, UserService userService) {
        this.authorRepository = authorRepository;
        this.userService = userService;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAllByOrderByFirstName();
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
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
