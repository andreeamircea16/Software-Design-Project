package com.store.app.service;

import com.store.app.utils.Constants;
import com.store.app.utils.MessageHandler;
import com.store.app.entity.Post;
import com.store.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private PostRepository postRepository;
    private AuthorService authorService;

    @Autowired
    public PostService(PostRepository postRepository, AuthorService authorService) {
        this.postRepository = postRepository;
        this.authorService = authorService;
    }

    public List<Post> getLatestPost(){
        return postRepository.findAllByOrderByDateDesc();
    }

    /**
     * @return an object which contains the request Http status, a success/error message and
     * the author object created in case of success
     */
    public ResponseEntity getPosts() {
        List<Post> posts = postRepository.findAllByOrderByModifiedDesc();
        if (posts.isEmpty()) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.NOT_FOUND, Constants.NOT_FOUND, null);
        }
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.OK, Constants.FOUND, posts);
    }

    /**
     * @param slug the slug of the post to be found
     * @return an object which contains the request Http status, a success/error message and
     * the post object in case of success
     */
    public ResponseEntity getPostBySlug(String slug) {
        if (slug == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.BAD_REQUEST, Constants.NOT_FOUND, null);
        }

        Post post = postRepository.findBySlug(slug);
        if (post == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.NOT_FOUND, Constants.NOT_FOUND, null);
        }
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.OK, Constants.FOUND, post);
    }

     /**
     * @param post the new post to be created
     * @return an object which contains the request Http status, a success/error message and
     * the post object created in case of success
     */
    public ResponseEntity createPost(Post post) {
        // TODO: Validations

        Post newPost = postRepository.save(post);
        if (newPost == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.BAD_REQUEST, Constants.NOT_CREATED, null);
        }
        this.authorService.notifySubscribers(post);

        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.CREATED, Constants.CREATED, post);
    }

    /**
     * @param id the id of the post to be deleted
     * @return an object which contains the request Http status, a success/error message
     */
    public ResponseEntity deletePost(UUID id) {
        if (id == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.BAD_REQUEST, Constants.NOT_FOUND, null);
        }

        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return MessageHandler.responseErrorMessageBuilder(HttpStatus.NOT_FOUND, Constants.NOT_FOUND, null);
        }

        postRepository.deleteById(id);
        return MessageHandler.responseSuccessMessageBuilder(HttpStatus.OK, Constants.DELETED, null);
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
