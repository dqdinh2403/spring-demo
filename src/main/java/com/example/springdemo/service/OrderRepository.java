package com.example.springdemo.service;

import com.example.springdemo.domain.Order;

public interface OrderRepository {

    Order save(Order order);

}
