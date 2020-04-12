package com.store.app.service;

import com.store.app.entity.Author;
import com.store.app.entity.Post;
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

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
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
     * @param author the new author to be created
     * @return an object which contains the request Http status, a success/error message and
     * the author object created in case of success
     */
    public ResponseEntity createAuthor(Author author) {
        // TO DO: Validations

        Author newAuthor = authorRepository.save(author);
        if (newAuthor == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.BAD_REQUEST, Constants.NOT_CREATED, null);
        }
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.CREATED, Constants.CREATED, author);
    }

    /**
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
}
