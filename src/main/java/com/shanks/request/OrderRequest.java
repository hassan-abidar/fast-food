package com.shanks.request;

import com.shanks.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long RestaurantId;
    private Address deliveryAddress;

}
