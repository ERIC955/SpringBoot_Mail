package com.eric.springbootmall.service;

import com.eric.springbootmall.dto.CreateOrderRequest;
import com.eric.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
