package com.shanks.request;

import lombok.Data;

@Data
public class IngredientCategoryRequest {
    String name;
    Long restaurantId;
}
