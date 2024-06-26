package com.restFaceBookApp.restFaceBook.repositories;

import com.restFaceBookApp.restFaceBook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
