package com.oltruong.bookstore.service;

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
            bookRepository.findById(orderLine.getBook().getId())
                          .ifPresent(orderLine::setBook);

            orderLineRepository.save(orderLine);
        }
        orderRepository.save(order);
    }

}
