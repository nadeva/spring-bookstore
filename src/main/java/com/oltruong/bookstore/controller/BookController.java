package com.oltruong.bookstore.controller;

import com.oltruong.bookstore.exception.ResourceNotFoundException;
import com.oltruong.bookstore.model.Book;
import com.oltruong.bookstore.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/rest/books", method = RequestMethod.GET)
    Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/rest/books", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createOperation(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @RequestMapping(value = "/rest/books/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        Book book = findBookOrThrowException(id);
        bookRepository.delete(book);
    }

    private Book findBookOrThrowException(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(value = "/rest/books/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    Book get(@PathVariable Long id) {
        return findBookOrThrowException(id);
    }

    @RequestMapping(value = "/rest/books/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void editBook(@RequestBody Book book, @PathVariable Long id) {
        findBookOrThrowException(id);
        book.setId(id);
        bookRepository.save(book);

    }


}