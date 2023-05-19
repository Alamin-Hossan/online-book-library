package com.alamin.onlinebooklibrary.controller;

import com.alamin.onlinebooklibrary.dto.BookDto;
import com.alamin.onlinebooklibrary.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        BookDto createdBook = bookService.createBook(bookDto);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/id/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) {
        BookDto book = bookService.getBookById(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/author/{authorName}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable String authorName) {
        List<BookDto> books = bookService.getBooksByAuthor(authorName);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{authorName}/{bookName}")
    public ResponseEntity<BookDto> getBookByAuthorAndName(@PathVariable String authorName, @PathVariable String bookName) {
        BookDto book = bookService.getBookByAuthorAndName(authorName, bookName);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        BookDto updatedBook = bookService.updateBook(id, bookDto);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
