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
            firstBook.setName("I ❤️ Spring");
            firstBook.setAuthor("Bob");
            firstBook.setPrice(42f);
            repository.save(firstBook);

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Book book : repository.findAll()) {
                log.info(book.toString());
            }
            log.info("");

//            // fetch an individual customer by ID
//            Customer customer = repository.findById(1L);
//            log.info("Customer found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(customer.toString());
//            log.info("");
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            repository.findByLastName("Bauer").forEach(bauer -> {
//                log.info(bauer.toString());
//            });
//            // for (Customer bauer : repository.findByLastName("Bauer")) {
//            //  log.info(bauer.toString());
//            // }
//            log.info("");
        };
    }
}
