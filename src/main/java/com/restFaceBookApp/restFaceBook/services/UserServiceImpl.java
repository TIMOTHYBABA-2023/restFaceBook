package com.restFaceBookApp.restFaceBook.services;

import com.restFaceBookApp.restFaceBook.models.User;
import com.restFaceBookApp.restFaceBook.repositories.UserRepository;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    @Override
    public boolean createUser(User user) {
        if (user != null){
         userRepository.save(user);
         return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user, Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            User user1 = optionalUser.get();
            user1.setUpdatedAt(user.getUpdatedAt());
            user1.setFirstname(user.getFirstname());
            user1.setLastname(user.getLastname());
            user1.setAge(user.getAge());
            user1.setEmail(user.getEmail());
            user1.setGender(user.getGender());
            user1.setPassword(user.getPassword());
            user1.setPosts(user.getPosts());

            userRepository.save(user1);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null){
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
