package com.oltruong.bookstore.repository;

import com.oltruong.bookstore.model.Book;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByName(String name);
}
