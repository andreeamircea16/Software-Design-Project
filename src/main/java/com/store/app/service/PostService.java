package com.store.app.service;

import com.store.app.entity.Post;
import com.store.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Post deletePost(String slug) {
        return postRepository.deleteBySlug(slug);
    }
}
