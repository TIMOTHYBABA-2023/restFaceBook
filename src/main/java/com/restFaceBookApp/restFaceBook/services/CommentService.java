package com.restFaceBookApp.restFaceBook.services;

import com.restFaceBookApp.restFaceBook.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentByUserIdAndPostId(Long userId, Long postId);

    boolean createComment(Comment comment, Long userId, Long postId);

    boolean updateComment(Comment comment, Long userId, Long postId, Long commentId);

    boolean deleteComment(Long postId, Long userId, Long commentId);

    List<Comment> findAllComment();
}
