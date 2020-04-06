package com.store.app.repository;

import com.store.app.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByOrderByDateDesc();

    List<Post> findAllByOrderByModifiedDesc();

    Post findBySlug(String slug);

    Post deleteBySlug(String slug);
}
