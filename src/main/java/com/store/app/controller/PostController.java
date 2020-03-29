package com.store.app.controller;

import com.store.app.entity.Post;
import com.store.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getPosts() {
        return postService.getPosts();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.PUT)
    public Post updatePost(@PathVariable(value = "uuid") UUID uuid, @RequestBody Post post) {
        Post newPost = postService.updatePost(uuid, post);
        return newPost;
    }

    @RequestMapping(value = "/slug/{slug}", method = RequestMethod.GET)
    public ResponseEntity postBySlug(@PathVariable(value = "slug") String slug) {
        return postService.getPostBySlug(slug);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE)
    public ResponseEntity deletePost(@PathVariable(value = "uuid") UUID uuid) {
        return postService.deletePost(uuid);
    }

    @RequestMapping(value = "/slug/{slug}", method = RequestMethod.DELETE)
    public Post deletePostBySlug(@PathVariable(value = "slug") String slug) {
        Post newPost = postService.deletePostBySlug(slug);
        return newPost;
    }
}
