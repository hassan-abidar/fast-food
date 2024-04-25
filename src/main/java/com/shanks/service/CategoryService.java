package com.shanks.service;

import com.shanks.model.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory(String name, Long UserId) throws Exception;
    public Category findCategoryById(Long categoryId) throws  Exception;
    public List<Category> findCategoryByRestaurantId(Long RestaurantId) throws Exception;

}
