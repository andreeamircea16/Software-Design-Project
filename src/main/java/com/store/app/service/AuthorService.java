package com.store.app.service;

import com.store.app.entity.Author;
import com.store.app.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAllByOrderByFirstName();
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }
}
