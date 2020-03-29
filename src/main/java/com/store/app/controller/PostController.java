package com.store.app.controller;

import com.store.app.entity.Post;
import com.store.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Post> getPosts() {
        List<Post> posts = postService.getPosts();
        return posts;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Post createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post);
        return newPost;
    }

    @RequestMapping(value = "/{slug}", method = RequestMethod.GET)
    public Post postBySlug(@PathVariable(value = "slug") String slug) {
        Post post = postService.getPostBySlug(slug);
        return post;
    }

    @RequestMapping(value = "/{slug}", method = RequestMethod.DELETE)
    public Post deletePost(@PathVariable(value = "slug") String slug) {
        Post newPost = postService.deletePost(slug);
        return newPost;
    }
}
