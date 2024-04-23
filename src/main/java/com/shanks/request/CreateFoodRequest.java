package com.shanks.request;


import com.shanks.model.Category;
import com.shanks.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    public String name;
    public String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean vegeterian;
    private boolean seasonal;
    private List<IngredientsItem> ingredients;

}
