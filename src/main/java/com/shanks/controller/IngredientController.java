package com.shanks.controller;

import com.shanks.model.IngredientCategory;
import com.shanks.model.IngredientsItem;
import com.shanks.request.IngredientCategoryRequest;
import com.shanks.request.IngredientItemRequest;
import com.shanks.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")

public class IngredientController {
    @Autowired
    private IngredientsService ingredientsService;
    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest request
            ) throws Exception {
        IngredientCategory category = ingredientsService.createIngeredientCategory(request.getName(), request.getRestaurantId());
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
    @PostMapping
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientItemRequest request
    ) throws Exception {
        System.out.println("request Restaurant id  = " + request.getRestaurantId());
        System.out.println("request Name id  = " + request.getName());
        System.out.println("request Category id  = " + request.getCategoryId());
        IngredientsItem item = ingredientsService.createIngredientItem(request.getRestaurantId(), request.getName(), request.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
            @PathVariable Long  id
    ) throws Exception {
        IngredientsItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredients(
            @PathVariable Long  id
    ) throws Exception {
        List<IngredientsItem> ingredients = ingredientsService.findRestaurantIngredients(id);
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientsCategory(
            @PathVariable Long  id
    ) throws Exception {
        List<IngredientCategory> categories = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
