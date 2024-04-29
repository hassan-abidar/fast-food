package com.shanks.controller;

import com.shanks.model.Order;
import com.shanks.service.OrderService;
import com.shanks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam (required = false) String status) throws Exception {
        List<Order> orders = orderService.getRestaurantOrders(id,status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @PathVariable String orderStatus
          ) throws Exception {
        Order order = orderService.updateOrder(id,orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
