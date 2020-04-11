package com.store.app.repository;

import com.store.app.entity.Author;
import com.store.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    List<Author> findAllByOrderByFirstName();

    User saveSubscribedUser(User subscriber);

    void deleteSubscribedUserByUuid(UUID uuid);

    List<User> findAllSubscribedUsersByOrderByFirstName();
}
