package com.shanks.service;

import com.shanks.model.IngredientCategory;
import com.shanks.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {
    public IngredientCategory createIngeredientCategory(String name,Long restaurantId) throws Exception;
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientIte(Long restaurntId,String ingredientName,Long IngredientCategoryId) throws Exception;
    public List<IngredientsItem> findRestaurantIngredients(Long RestaurantId) throws Exception;

    public IngredientsItem updateStock(Long id) throws Exception;


}
