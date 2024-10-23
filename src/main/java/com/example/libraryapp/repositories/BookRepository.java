package com.example.libraryapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.java.com.librarybackend.library_backend.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books by title 
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Find books by author 
    List<Book> findByAuthorContainingIgnoreCase(String author);

    // Find books by genre 
    List<Book> findByGenreContainingIgnoreCase(String genre);
}
