package com.shanks.service;

import com.shanks.model.Category;
import com.shanks.model.Food;
import com.shanks.model.Restaurant;
import com.shanks.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createdFood(CreateFoodRequest request, Category category, Restaurant restaurant);

    public void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId,boolean isvegeterian , boolean isNonVeg,boolean isSeasonal,String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long FoodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;


}
