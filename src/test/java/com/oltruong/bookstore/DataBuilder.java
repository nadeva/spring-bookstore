package com.oltruong.bookstore;

import com.oltruong.bookstore.model.Book;
import com.oltruong.bookstore.model.Order;
import com.oltruong.bookstore.model.OrderLine;

import java.util.Arrays;

public class DataBuilder {

    public static Order buildOrder() {

        Book book = buildBook();

        return buildOrder(book);
    }

    public static Order buildOrder(Book book) {
        OrderLine orderLine = new OrderLine();
        orderLine.setBook(book);
        orderLine.setQuantity(Integer.valueOf(1));

        Order order = new Order();
        order.setOrderLines(Arrays.asList(orderLine));
        order.setName("Olivier");
        order.setAddress("France");
        return order;
    }

    public static Book buildBook() {

        Book book = new Book();
        book.setAuthor("Chris Schaefer , Clarence Ho , Rob Harrop");
        book.setName("Pro Spring");
        return book;
    }
}
