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

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    private  Long totalAmount;

    private String orderStatus;

    private Date createdAt;

    @ManyToOne
    private Adress deliveryAdress;

    @OneToMany
    private List<OrderItem> items ;

    //private  Payment payment;
    private int totalItems;

    private int totalPrice;
















}
