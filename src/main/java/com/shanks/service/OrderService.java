package com.shanks.service;

import com.shanks.model.Order;
import com.shanks.model.User;
import com.shanks.request.OrderRequest;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId,String orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUserOrder(Long userId) throws Exception;

    public List<Order> getRestaurantOrders(Long restaurantId,String orderStatus) throws Exception;
    public Order findOrderById(Long id) throws Exception;




}
