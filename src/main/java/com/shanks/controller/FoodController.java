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

import java.util.List;

@RestController
@RequestMapping("/api")

public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;


    @GetMapping ("/food/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
    @GetMapping ("food/restaurant/{id}")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam  (required = false) boolean vegeterian,
                                                        @RequestParam (required = false) boolean seasonal,
                                                        @RequestParam (required = false) boolean nonveg,
                                                        @RequestParam (required = false) String category,
                                                        @PathVariable Long id,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {

        User user= userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.getRestaurantFood(id,vegeterian,nonveg,seasonal,category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

}
