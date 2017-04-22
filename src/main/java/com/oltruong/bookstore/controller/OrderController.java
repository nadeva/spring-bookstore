package com.oltruong.bookstore.controller;

import com.oltruong.bookstore.exception.ResourceNotFoundException;
import com.oltruong.bookstore.service.OrderService;
import com.oltruong.bookstore.model.Order;
import com.oltruong.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/rest/orders", method = RequestMethod.GET)
    Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @RequestMapping(value = "/rest/orders", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createOperation(@RequestBody Order order) {
        orderService.save(order);
    }

    @RequestMapping(value = "/rest/orders/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {

        Order order = orderRepository.findOne(id);
        if (order != null) {
            orderRepository.delete(order);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/rest/orders/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    Order get(@PathVariable Long id) {
        Order order = orderRepository.findOne(id);
        if (order == null) {
            throw new ResourceNotFoundException();
        }
        return order;
    }

    @RequestMapping(value = "/rest/orders/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void editOrder(@RequestBody Order order, @PathVariable Long id) {

        if (orderRepository.findOne(id) == null) {
            throw new ResourceNotFoundException();
        }
        order.setId(id);
        orderRepository.save(order);

    }


}