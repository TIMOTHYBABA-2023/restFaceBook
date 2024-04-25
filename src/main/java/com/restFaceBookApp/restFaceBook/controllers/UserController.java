package com.restFaceBookApp.restFaceBook.controllers;

import com.restFaceBookApp.restFaceBook.models.Post;
import com.restFaceBookApp.restFaceBook.models.User;
import com.restFaceBookApp.restFaceBook.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
        private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUser(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = userService.getUserById(id);
        if (user != null){
            return  new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        boolean userCreated = userService.createUser(user);
        if (userCreated) {
            return new ResponseEntity<>("User successfully Created...", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User creation not successful...", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable Long id){

        boolean updatedUser = userService.updateUser(user, id);
        if (updatedUser)
            return new ResponseEntity<>("User successfully Updated...", HttpStatus.OK);

        return new ResponseEntity<>("User not found...", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){

        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return new ResponseEntity<>("User successfully deleted...", HttpStatus.OK);
        }
        else {return new ResponseEntity<>("User not found...", HttpStatus.NOT_FOUND);}
    }
}
