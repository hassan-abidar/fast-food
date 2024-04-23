package com.shanks.repository;

import com.shanks.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository <Restaurant,Long>{
    @Query("SELECT r from Restaurant r where lower(r.name) like lower(concat('%',:query,'%') ) " +
            "OR lower(r.cuisineType) LIKE lower(concat('%',:query,'%'))")
    List<Restaurant> findBySearchQuery(String query);
    Restaurant findByOwnerId(Long userId);

}
