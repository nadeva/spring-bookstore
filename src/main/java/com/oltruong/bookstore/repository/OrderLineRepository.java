package com.oltruong.bookstore.repository;

import com.oltruong.bookstore.model.OrderLine;
import org.springframework.data.repository.CrudRepository;

public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {
}
