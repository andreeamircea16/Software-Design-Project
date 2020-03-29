package com.store.app.service;

import com.store.app.entity.Post;
import com.store.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getLatestPost(){
        return postRepository.findAllByOrderByDateDesc();
    }

    public List<Post> getPosts() {
        return postRepository.findAllByOrderByModifiedDesc();
    }

    public Post getPostBySlug(String slug) {
        return postRepository.findBySlug(slug);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public ResponseEntity deletePost(UUID id) {
        if (id == null) {
            return null;
        }

        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return null;
        }

        postRepository.deleteById(id);
        return null;
    }

    public Post deletePostBySlug(String slug) {
        return postRepository.deleteBySlug(slug);
    }

    public Post updatePost(UUID id, Post post) {
        Post oldPost = postRepository.findById(id).orElse(new Post());
        oldPost.setUuid(oldPost.getUuid());
        return postRepository.save(post);
    }
}
