package com.alamin.onlinebooklibrary.repository;

import com.alamin.onlinebooklibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String authorName);

    boolean existsByAuthor(String authorName);

    Optional<Book> findByAuthorAndName(String authorName, String name);


}