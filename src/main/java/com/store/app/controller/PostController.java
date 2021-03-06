package com.store.app.controller;

import com.store.app.entity.Post;
import com.store.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getPosts() {
        return postService.getPosts();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.PUT)
    public Post updatePost(@PathVariable(value = "uuid") UUID uuid, @RequestBody Post post) {
        Post newPost = postService.updatePost(uuid, post);
        return newPost;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/slug/{slug}", method = RequestMethod.GET)
    public ResponseEntity postBySlug(@PathVariable(value = "slug") String slug) {
        return postService.getPostBySlug(slug);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE)
    public ResponseEntity deletePost(@PathVariable(value = "uuid") UUID uuid) {
        return postService.deletePost(uuid);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/slug/{slug}", method = RequestMethod.DELETE)
    public Post deletePostBySlug(@PathVariable(value = "slug") String slug) {
        Post newPost = postService.deletePostBySlug(slug);
        return newPost;
    }
}
