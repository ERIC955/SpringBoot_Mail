package com.eric.springbootmall.dao;

import com.eric.springbootmall.model.Order;
import com.eric.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemByOrderId(Integer orderId);

    Integer createOrder(Integer userId,Integer totalAmount);

    void  createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
