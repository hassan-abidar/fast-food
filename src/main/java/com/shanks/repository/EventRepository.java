package com.shanks.repository;

import com.shanks.model.Event;
import com.shanks.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByRestaurantId(Long id);

}
