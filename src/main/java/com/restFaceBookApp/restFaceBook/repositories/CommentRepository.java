package com.restFaceBookApp.restFaceBook.repositories;

import com.restFaceBookApp.restFaceBook.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllCommentByUserIdAndPostId(Long userId, Long postId);

    Comment findByIdAndUserIdAndPostId(Long commentId, Long userId, Long postId);

}
