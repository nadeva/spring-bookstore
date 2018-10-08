module com.oltruong.bookstore {
    exports com.oltruong.bookstore;
    opens com.oltruong.bookstore.service;

    requires java.persistence;
    requires java.validation;

    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.data.commons;
    requires spring.web;
}