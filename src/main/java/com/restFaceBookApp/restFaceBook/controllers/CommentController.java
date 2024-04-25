package com.restFaceBookApp.restFaceBook.controllers;

import com.restFaceBookApp.restFaceBook.models.Comment;
import com.restFaceBookApp.restFaceBook.models.Post;
import com.restFaceBookApp.restFaceBook.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> findAllComment(){
            List<Comment> commentList = commentService.findAllComment();
        if (commentList != null) {
            return new ResponseEntity<>(commentList, HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<List<Comment>> findAllCommentByUserIdAndPostId(@PathVariable Long userId, @PathVariable Long postId){
            List<Comment> commentList = commentService.getAllCommentByUserIdAndPostId(userId, postId);
        if (commentList != null) {
            return new ResponseEntity<>(commentList, HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<String> createComment(@RequestBody Comment comment, @PathVariable Long userId, @PathVariable Long postId){

        boolean commentCreated = commentService.createComment(comment, userId, postId);
        if (commentCreated)
            return new ResponseEntity<>("Comment successfully created...", HttpStatus.CREATED);

        return new ResponseEntity<>("Comment creation not successful...", HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/{commentId}/user/{userId}/post/{postId}")
    public ResponseEntity<String> updateComment(@RequestBody Comment comment, @PathVariable Long userId, @PathVariable Long postId, @PathVariable Long commentId){

        boolean updatedComment = commentService.updateComment(comment, userId, postId, commentId);
        if (updatedComment) {
            return new ResponseEntity<>("Comment successfully Updated...", HttpStatus.OK);
        }else return new ResponseEntity<>("Comment or User not found...", HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/{commentId}/user/{userId}/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId, @PathVariable Long userId, @PathVariable Long commentId){

        boolean deleted = commentService.deleteComment(postId, userId, commentId);
        if (deleted)
            return new ResponseEntity<>("Comment successfully deleted...", HttpStatus.OK);

        return new ResponseEntity<>("Comment not found...", HttpStatus.NOT_FOUND);
    }

}
