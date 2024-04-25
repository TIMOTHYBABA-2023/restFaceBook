package com.restFaceBookApp.restFaceBook.repositories;

import com.restFaceBookApp.restFaceBook.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPostIdAndUserId(Long postId, Long userId);

    Like findByIdAndPostIdAndUserId(Long likeId, Long postId, Long userId);
}
