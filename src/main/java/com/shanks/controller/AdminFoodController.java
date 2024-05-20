package com.shanks.controller;

import com.shanks.model.Food;
import com.shanks.model.Restaurant;
import com.shanks.model.User;
import com.shanks.request.CreateFoodRequest;
import com.shanks.response.MessageResponse;
import com.shanks.service.FoodService;
import com.shanks.service.RestaurantService;
import com.shanks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
        Food food = foodService.createdFood(request,request.getCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("Food deleted successfully");


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Food> updateFoodAvailibiltyStatus(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
