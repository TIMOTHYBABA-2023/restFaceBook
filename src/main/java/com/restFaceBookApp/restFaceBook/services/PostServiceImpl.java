package com.restFaceBookApp.restFaceBook.services;

import com.restFaceBookApp.restFaceBook.models.Post;
import com.restFaceBookApp.restFaceBook.models.User;
import com.restFaceBookApp.restFaceBook.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserService userService;


    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }



    @Override
    public List<Post> getAllPosts(Long userId) {
        User user = userService.getUserById(userId);

        if (user != null)
            return postRepository.findAllPostByUserId(userId);
        else return null;
    }

    @Override
    public boolean createPost(Post post, Long userId) {
        User user = userService.getUserById(userId);
        if (user != null){
            post.setUser(user);
            postRepository.save(post);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePost(Post post, Long postId, Long userId) {
        Optional<Post> optionalPost= postRepository.getPostByIdAndUserId(postId, userId);
        Post post1 = null;
        User user = userService.getUserById(userId);
        if (user != null){
            if (optionalPost.isPresent()){
                post1 = optionalPost.get();
                post1.setContent(post.getContent());

                postRepository.save(post1);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePost(Long postId, Long userId) {
        Optional<Post> optionalPost= postRepository.getPostByIdAndUserId(postId, userId);
        if (optionalPost.isPresent()){
            Post deletePost = optionalPost.get();
            postRepository.delete(deletePost);
            return true;
        }
        return false;
    }

    @Override
    public Post getPostByIdAndUserId(Long postId, Long userId) {
        return postRepository.getPostByIdAndUserId(postId, userId).orElse(null);
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.getPostById(postId).orElse(null);
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

}
