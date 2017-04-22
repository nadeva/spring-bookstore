package com.oltruong.bookstore.repository;

import com.oltruong.bookstore.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
