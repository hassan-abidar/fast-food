package com.shanks.request;

import com.shanks.model.Address;
import com.shanks.model.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    public Long RestaurantId;
    public Address deliveryAddress;
}
