package com.alamin.onlinebooklibrary.service;

import com.alamin.onlinebooklibrary.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookById(Long bookId);

    List<BookDto> getBooksByAuthor(String authorName);

    BookDto getBookByAuthorAndName(String authorName, String bookName);

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(Long id, BookDto bookDto);

    void deleteBook(Long id);
}
