package com.store.app.repository;

import com.store.app.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends CrudRepository<Author, UUID> {
    List<Author> findAllByOrderByFirstName();
}
