package com.oltruong.bookstore.repository;

import com.oltruong.bookstore.DataBuilder;
import com.oltruong.bookstore.MyContext;
import com.oltruong.bookstore.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyContext.class})
public class BookRepositoryIT {

    @Autowired
    BookRepository bookRepository;


    @Test
    public void createBook() {

        Book book = DataBuilder.buildBook();
        assertThat(book.getId()).as("Book ID").isNull();

        bookRepository.save(book);
        assertThat(book.getId()).as("Book ID").isNotNull();

        Book bookFromDatabase = bookRepository.findOne(book.getId());
        assertThat(bookFromDatabase).as("Book from Database").isEqualTo(book);

        bookRepository.delete(bookFromDatabase);
        assertThat(bookRepository.findOne(book.getId())).isNull();


    }

}
