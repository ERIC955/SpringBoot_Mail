package com.eric.springbootmall.service.impl;

import com.eric.springbootmall.dao.OrderDao;
import com.eric.springbootmall.dao.ProductDao;
import com.eric.springbootmall.dto.BuyItem;
import com.eric.springbootmall.dto.CreateOrderRequest;
import com.eric.springbootmall.model.Order;
import com.eric.springbootmall.model.OrderItem;
import com.eric.springbootmall.model.Product;
import com.eric.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem:createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            //轉換
            OrderItem orderItem = new OrderItem();

            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        //創建訂單
        Integer orderId = orderDao.createOrder(userId , totalAmount);

        orderDao.createOrderItems(orderId,orderItemList);

        return orderId;
    }
}
