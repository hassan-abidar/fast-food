package com.shanks.request;

import com.shanks.model.Address;
import com.shanks.model.ContactInformtion;
import lombok.Data;

import java.util.List;

@Data


public class CreateRestaurantRequest {
    private Long id ;
    private String name;
    private String description;
    private Address address;
    private ContactInformtion contactInformtion;
    private String OpeningHours;
    private List<String> images;
    private String CuisineType ;

}
