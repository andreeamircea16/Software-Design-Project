package com.store.app.controller;

import com.store.app.entity.Author;
import com.store.app.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getAuthors() {
        return authorService.getAuthors();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAuthor(@PathVariable(value = "uuid") UUID uuid) {
        return authorService.deleteAuthor(uuid);
    }
}
