package com.shanks.service;

import com.shanks.dto.RestaurantDto;
import com.shanks.model.Restaurant;
import com.shanks.model.User;
import com.shanks.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req , User user);
    public Restaurant updateRestaurant (Long restaurantId , CreateRestaurantRequest updateRequest) throws Exception;
    public void deleteRestaurant (Long restaurantId) throws Exception;
    public List<Restaurant> getAllRestaurants();
    public List<Restaurant> searchRestaurant(String keyword);
    public Restaurant findRestaurantById(Long id) throws Exception;
    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId,User user) throws Exception;
    public Restaurant updateRestaurantStatus(Long id) throws Exception;






}
