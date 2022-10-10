package com.oltruong.bookstore;

import com.oltruong.bookstore.model.Book;
import com.oltruong.bookstore.repository.BookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository repository) {
        return (args) -> {
            // save a few customers
            Book firstBook = new Book();
            firstBook.setName("I ❤️Spring");
            firstBook.setAuthor("Bob");
            firstBook.setDescription("A great book to fall in love with Spring");
            firstBook.setPrice(45f);
            repository.save(firstBook);

            Book secondBook = new Book();
            secondBook.setName("Pro Spring 6: An In-Depth Guide");
            secondBook.setAuthor("Iuliana Cosmina");
            secondBook.setDescription("Master the Spring Framework.");
            secondBook.setPrice(54.12f);
            secondBook.setPictureURL("https://m.media-amazon.com/images/I/41t+YkLzIrL._AC_SY780_.jpg");
            repository.save(secondBook);

            // fetch all customers
            log.info("Books found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(book -> log.info(book.toString()));
        };
    }
}
