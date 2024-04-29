package com.shanks.service;

import com.shanks.model.*;
import com.shanks.repository.*;
import com.shanks.request.OrderRequest;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CartService cartService;


    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address deliveryAddress = order.getDeliveryAddress();
        Address savedAddress = adressRepository.save(deliveryAddress);
        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }
        System.out.println("Debugging : "+order.getRestaurantId());
        System.out.println("Debugging : "+order.getDeliveryAddress().getPostalCode());

        Restaurant restaurant=restaurantService.findRestaurantById(order.getRestaurantId());
        Order createdOrder = new Order();
        createdOrder.setCreatedAt(new Date());
        createdOrder.setCustomer(user);
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setRestaurant(restaurant);
        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem cartItem : cart.getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }
        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(cart.getTotal());
        createdOrder.setTotalItems(cart.getItems().size());

        Order savedOrder = orderRepository.save(createdOrder);
        restaurant.getOrders().add(savedOrder);

        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {

        Optional<Order>  order = orderRepository.findById(orderId);
        if(order.isEmpty()){
            throw new Exception("Order not found !!");
        }
        Order savedOrder = order.get();
        if(orderStatus.equals("OUT_FOR_DELIVERY")
            || orderStatus.equals("DELIVERED")
            || orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING")
        ){
            savedOrder.setOrderStatus(orderStatus);
            return orderRepository.save(savedOrder);
        }
        throw new Exception("Please enter a valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrder(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new Exception("user not found with id : "+userId);
        User foundUser = user.get();
        List<Order> orders = foundUser.getOrders();
        return orders;
    }

    @Override
    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        List<Order> orders = restaurant.getOrders();
        if(orderStatus!=null){
        orders =  orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).toList();
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long id) throws Exception {
        Optional<Order> optionalOrders = orderRepository.findById(id);
        if(optionalOrders.isEmpty())
            throw new Exception("Order not found with id : "+id);

        return optionalOrders.get();
    }
}
