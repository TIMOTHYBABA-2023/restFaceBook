package com.restFaceBookApp.restFaceBook.services;

import com.restFaceBookApp.restFaceBook.models.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts(Long userId);

    boolean createPost(Post post, Long userId);

    boolean updatePost(Post post, Long postId, Long userId);

    boolean deletePost(Long postId, Long userId);


    Post getPostByIdAndUserId(Long postId, Long userId);

    Post getPostById(Long postId);

    List<Post> findAllPost();
}
