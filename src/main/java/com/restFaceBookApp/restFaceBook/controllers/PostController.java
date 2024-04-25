package com.restFaceBookApp.restFaceBook.controllers;

import com.restFaceBookApp.restFaceBook.models.Post;
import com.restFaceBookApp.restFaceBook.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPost(){
        List<Post> postByUserId = postService.findAllPost();
        if (postByUserId != null)
            return new ResponseEntity<>(postByUserId, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> findAllPostByUserId(@PathVariable Long userId){
        List<Post> postByUserId = postService.getAllPosts(userId);
        if (postByUserId != null)
            return new ResponseEntity<>(postByUserId, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Long postId){
        Post postByUserId = postService.getPostById(postId);
        if (postByUserId != null)
            return new ResponseEntity<>(postByUserId, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{postId}/user/{userId}")
    public ResponseEntity<Post> getPostByIdAndUserId(@PathVariable Long postId, @PathVariable Long userId){
        Post post = postService.getPostByIdAndUserId(postId, userId);
        if (post != null){
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<String> createPost(@RequestBody Post post, @PathVariable Long userId){

        boolean postCreated = postService.createPost(post, userId);
        if (postCreated)
            return new ResponseEntity<>("Post successfully Created...", HttpStatus.CREATED);

        return new ResponseEntity<>("Post creation not successful...", HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/{postId}/user/{userId}")
    public ResponseEntity<String> updatePost(@RequestBody Post post, @PathVariable Long postId, @PathVariable Long userId){

        boolean updatedPost = postService.updatePost(post, postId, userId);
        if (updatedPost)
            return new ResponseEntity<>("Post successfully Updated...", HttpStatus.OK);

        return new ResponseEntity<>("Post or User not found...", HttpStatus.NOT_FOUND);
    }





    @DeleteMapping("/{postId}/user/{userId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId, @PathVariable Long userId){

        boolean deleted = postService.deletePost(postId, userId);
        if (deleted)
            return new ResponseEntity<>("Post successfully deleted...", HttpStatus.OK);

        return new ResponseEntity<>("Post not found...", HttpStatus.NOT_FOUND);
    }

}
