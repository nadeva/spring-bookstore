package com.oltruong.bookstore.service;

import com.oltruong.bookstore.model.Book;
import com.oltruong.bookstore.model.Order;
import com.oltruong.bookstore.model.OrderLine;
import com.oltruong.bookstore.repository.BookRepository;
import com.oltruong.bookstore.repository.OrderLineRepository;
import com.oltruong.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    public void save(Order order) {
        order.setCreationDate(new Date());
        for (OrderLine orderLine : order.getOrderLines()) {
            Book book = bookRepository.findOne(orderLine.getBook().getId());
            orderLine.setBook(book);
            orderLineRepository.save(orderLine);
        }
        orderRepository.save(order);
    }
}
