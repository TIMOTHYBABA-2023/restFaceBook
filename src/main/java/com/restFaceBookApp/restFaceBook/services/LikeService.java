package com.restFaceBookApp.restFaceBook.services;

public interface LikeService {
    public boolean likePost(Long userId, Long postId);

    boolean unlikePost(Long userId, Long postId, Long likeId);
}
