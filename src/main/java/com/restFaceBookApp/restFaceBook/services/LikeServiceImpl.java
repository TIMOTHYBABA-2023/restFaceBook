package com.restFaceBookApp.restFaceBook.services;

import com.restFaceBookApp.restFaceBook.models.Like;
import com.restFaceBookApp.restFaceBook.models.Post;
import com.restFaceBookApp.restFaceBook.models.User;
import com.restFaceBookApp.restFaceBook.repositories.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{
    private final LikeRepository likeRepository;
    private final PostService postService;
    private final UserService userService;

    public LikeServiceImpl(LikeRepository likeRepository, PostService postService, UserService userService) {
        this.likeRepository = likeRepository;
        this.postService = postService;
        this.userService = userService;
    }

    @Override
    public boolean likePost(Long userId, Long postId) {

        Post post = postService.getPostById(postId);
        User user = userService.getUserById(userId);
        if (post != null && user != null){
            boolean checkLikeStatus1 = checkLikeStatus(userId, postId);
            if (!checkLikeStatus1){
                Like like = new Like();
                like.setPost(post);
                like.setUser(user);
                likeRepository.save(like);
                return true;
            }
        }return false;
    }

    @Override
    public boolean unlikePost(Long userId, Long postId, Long likeId) {
        Like like = likeRepository.findByIdAndPostIdAndUserId(likeId, postId, userId);
            if (like != null) {
                likeRepository.delete(like);
                return true;
            }
        return false;
    }

    private boolean checkLikeStatus(Long userId, Long postId) {
        return likeRepository.existsByPostIdAndUserId(postId, userId);
    }
}
