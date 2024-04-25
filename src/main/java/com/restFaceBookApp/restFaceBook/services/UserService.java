package com.restFaceBookApp.restFaceBook.services;

import com.restFaceBookApp.restFaceBook.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    boolean createUser(User user);
    boolean updateUser(User user, Long id);
    boolean deleteUserById(Long id);

    User getUserById(Long id);
}
