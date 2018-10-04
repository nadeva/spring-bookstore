package com.oltruong.bookstore.service;

import com.oltruong.bookstore.DataBuilder;
import com.oltruong.bookstore.model.Book;
import com.oltruong.bookstore.model.Order;
import com.oltruong.bookstore.repository.BookRepository;
import com.oltruong.bookstore.repository.OrderLineRepository;
import com.oltruong.bookstore.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    BookRepository mockBookRepository;

    @Mock
    OrderRepository mockOrderRepository;

    @Mock
    OrderLineRepository mockOrderLineRepository;

    @Test
    public void save() {

        //GIVEN
        Date beginDate = new Date();

        Book book = DataBuilder.buildBook();

        final Long bookId = Long.valueOf(123l);
        book.setId(bookId);
        Order order = DataBuilder.buildOrder(book);
        assertThat(order.getCreationDate()).as("Order ID").isNull();
        when(mockBookRepository.findByName(isA(String.class))).thenReturn(order.getOrderLines().get(0).getBook());
        when(mockBookRepository.findById(isA(Long.class))).thenReturn(Optional.empty());

        OrderService orderService = new OrderService();

        ReflectionTestUtils.setField(orderService, "bookRepository", mockBookRepository);
        ReflectionTestUtils.setField(orderService, "orderRepository", mockOrderRepository);
        ReflectionTestUtils.setField(orderService, "orderLineRepository", mockOrderLineRepository);


        //WHEN
        orderService.save(order);

        //THEN
        verify(mockBookRepository).findById(eq(bookId));
        verify(mockOrderLineRepository).save(eq(order.getOrderLines().get(0)));
        verify(mockOrderRepository).save(eq(order));

        assertThat(order.getCreationDate()).isAfterOrEqualsTo(beginDate);
    }


}
