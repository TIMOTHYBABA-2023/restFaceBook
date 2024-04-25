package com.restFaceBookApp.restFaceBook.controllers;

import com.restFaceBookApp.restFaceBook.services.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<String> likePost(@PathVariable Long userId, @PathVariable Long postId){
        boolean likedPost = likeService.likePost(userId, postId);
        if (likedPost){
            return new ResponseEntity<>("Post has been liked Successfully", HttpStatus.OK);
        }else return new ResponseEntity<>("Could not like post", HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{likeId}/user/{userId}/post/{postId}")
    public ResponseEntity<String> unlikePost(@PathVariable Long userId, @PathVariable Long postId, @PathVariable Long likeId){
        boolean unlikePost = likeService.unlikePost(userId, postId, likeId);
        if (unlikePost){
            return new ResponseEntity<>("Post has been unliked Successfully", HttpStatus.OK);
        }else return new ResponseEntity<>("Post could not be unliked", HttpStatus.NOT_IMPLEMENTED);

    }
}
