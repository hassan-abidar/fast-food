package com.shanks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")


public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    private String orderStatus;

    private Date createdAt;

    @ManyToOne
    private Address deliveryAddress;

    @OneToMany
    private List<OrderItem> items ;

    //private  Payment payment;
    private int totalItems;

    private Long totalPrice;
















}
