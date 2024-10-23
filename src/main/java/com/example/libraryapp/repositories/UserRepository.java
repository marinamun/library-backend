package com.example.libraryapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.java.com.librarybackend.library_backend.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username
    List<User> findByUsernameContainingIgnoreCase(String username);

    // Find users by email
    List<User> findByEmailContainingIgnoreCase(String email);

}
