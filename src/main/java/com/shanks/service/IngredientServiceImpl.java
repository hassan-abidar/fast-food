package com.shanks.service;

import com.shanks.model.IngredientCategory;
import com.shanks.model.IngredientsItem;
import com.shanks.model.Restaurant;
import com.shanks.repository.IngredientCategoryRepository;
import com.shanks.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service


public class IngredientServiceImpl implements IngredientsService{

   @Autowired
    private IngredientItemRepository ingredientItemRepository;
   @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;
   @Autowired
   private RestaurantService restaurantService;
    @Override
    public IngredientCategory createIngeredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setRestaurant(restaurant);
        ingredientCategory.setName(name);
        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);
        if(opt.isEmpty())
            throw new Exception("Ingredient category not found !!");
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientIte(Long restaurantId, String ingredientName, Long IngredientCategoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(IngredientCategoryId);
        IngredientsItem ingredientsItem = new IngredientsItem();
        ingredientsItem.setRestaurant(restaurant);
        ingredientsItem.setName(ingredientName);
        ingredientsItem.setCategory(category);

        IngredientsItem ingredient = ingredientItemRepository.save(ingredientsItem);
        category.getIngredients().add(ingredient);
        return ingredient;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) throws Exception {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> opt = ingredientItemRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Ingredient Item not found !!");
        }
        IngredientsItem ingredientsItem = opt.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());

        return ingredientItemRepository.save(ingredientsItem);
    }
}
