package com.store.app.controller;

import com.store.app.entity.Author;
import com.store.app.entity.User;
import com.store.app.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Author> getAuthors() {
        List<Author> authors = authorService.getAuthors();
        return authors;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Author createPost(@RequestBody Author author) {
        Author newAuthor = authorService.createAuthor(author);
        return newAuthor;
    }

    @RequestMapping(value = "/subscribeUser", method = RequestMethod.PATCH)
    public User addSubscriber(@RequestBody User subscriber) {
         return authorService.subscribe(subscriber);
    }
}
