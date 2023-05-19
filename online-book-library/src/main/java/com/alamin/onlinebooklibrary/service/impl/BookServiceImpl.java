package com.alamin.onlinebooklibrary.service.impl;

import com.alamin.onlinebooklibrary.dto.BookDto;
import com.alamin.onlinebooklibrary.entity.Book;
import com.alamin.onlinebooklibrary.exceptions.BookAlreadyExistsException;
import com.alamin.onlinebooklibrary.exceptions.BookCreationException;
import com.alamin.onlinebooklibrary.exceptions.BookNotFoundException;
import com.alamin.onlinebooklibrary.exceptions.BookNotFoundExceptionWithAuthorAndName;
import com.alamin.onlinebooklibrary.repository.BookRepository;
import com.alamin.onlinebooklibrary.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper){
        this.bookRepository = bookRepository;
        this.modelMapper= modelMapper;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()){
            throw new BookNotFoundException("No books found.", "Please add some book first.");
        }
        return books.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    @Override
    public BookDto getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " , bookId));
        return modelMapper.map(book, BookDto.class);


    }

    @Override
    public List<BookDto> getBooksByAuthor(String authorName) {
        List<Book> books = bookRepository.findByAuthor(authorName);
        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found with Author: " , authorName);
        }
        return books.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookByAuthorAndName(String authorName, String bookName) {
        Book book = bookRepository.findByAuthorAndName(authorName, bookName)
                .orElseThrow(() -> new BookNotFoundExceptionWithAuthorAndName("Book not found with author: " , authorName , " and name: " , bookName));

        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        if (bookRepository.existsByAuthor(bookDto.getAuthor())) {
            throw new BookAlreadyExistsException("Book already exists with title: " , bookDto.getAuthor());
        }
        Book book = convertToEntity(bookDto);
        Book savedBook;
        try {
            savedBook = bookRepository.save(book);
        } catch (Exception ex) {
            throw new BookCreationException("Failed to create book.");
        }
        return convertToDto(savedBook);
    }


  /*  @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

        modelMapper.map(bookDto, existingBook);
        Book updatedBook = bookRepository.save(existingBook);

        return modelMapper.map(updatedBook, BookDto.class);
    }*/

    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElse (null);
        if (book != null) {
            book.setName(bookDto.getName());
            book.setAuthor(bookDto.getAuthor());
            book.setPrice(bookDto.getPrice());
            book.setYear(bookDto.getYear());
            book.setDescription(bookDto.getDescription());
            Book updatedBook = bookRepository.save(book);
            return convertToDto(updatedBook);
        }

        return bookDto;
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with ID: " , id);
        }
        bookRepository.deleteById(id);
    }

    private BookDto convertToDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    private Book convertToEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }
}
