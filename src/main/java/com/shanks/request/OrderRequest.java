package com.shanks.request;

import com.shanks.model.Address;
import lombok.Data;
@Data
public class OrderRequest {
    public Long RestaurantId;
    public Address deliveryAddress;
}
