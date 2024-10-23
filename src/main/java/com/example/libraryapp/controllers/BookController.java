package com.example.libraryapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.libraryapp.repositories.BookRepository;

import main.java.com.librarybackend.library_backend.model.Book;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Create a new book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    // Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedBook.setId(id); // Set the ID of the book to update
        Book book = bookRepository.save(updatedBook);
        return ResponseEntity.ok(book);
    }

    // Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books);
    }

    // Find books by title
    @GetMapping("/search")
    public ResponseEntity<List<Book>> findBooksByTitle(@RequestParam String title) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        return ResponseEntity.ok(books);
    }

    // Find books by author
    @GetMapping("/search/author")
    public ResponseEntity<List<Book>> findBooksByAuthor(@RequestParam String author) {
        List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);
        return ResponseEntity.ok(books);
    }

    // Find books by genre
    @GetMapping("/search/genre")
    public ResponseEntity<List<Book>> findBooksByGenre(@RequestParam String genre) {
        List<Book> books = bookRepository.findByGenreContainingIgnoreCase(genre);
        return ResponseEntity.ok(books);
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
