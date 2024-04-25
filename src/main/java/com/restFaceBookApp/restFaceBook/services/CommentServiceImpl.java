package com.restFaceBookApp.restFaceBook.services;

import com.restFaceBookApp.restFaceBook.models.Comment;
import com.restFaceBookApp.restFaceBook.models.Post;
import com.restFaceBookApp.restFaceBook.models.User;
import com.restFaceBookApp.restFaceBook.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }


    @Override
    public List<Comment> getAllCommentByUserIdAndPostId(Long userId, Long postId) {
        Post post = postService.getPostById(postId);
        if (post != null){
            User user = userService.getUserById(userId);
            if (user == null){
                return null;
            }else return commentRepository.findAllCommentByUserIdAndPostId(userId, postId);
        }
        return null;
    }

    @Override
    public boolean createComment(Comment comment, Long userId, Long postId) {

        Post post = postService.getPostById(postId);
       if (post != null){
            User user = userService.getUserById(userId);
            if (user == null){
                return false;
            }else {
                comment.setPost(post);
                comment.setUser(user);

                commentRepository.save(comment);
                return true;
            }
       }
       return false;
    }


    public boolean updateComment(Comment comment, Long userId, Long postId, Long commentId) {
        Comment comment1 = commentRepository.findByIdAndUserIdAndPostId(commentId,userId,postId);

        if (comment1 !=null){
            comment1.setContent(comment.getContent());

            commentRepository.save(comment1);
            return true;
        }

        else return false;
    }

    @Override
    public boolean deleteComment(Long postId, Long userId, Long commentId) {
        Comment comment = commentRepository.findByIdAndUserIdAndPostId(commentId, userId, postId);
        if (comment != null){
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }

    @Override
    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }


}

