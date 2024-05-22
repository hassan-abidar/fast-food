package com.shanks.service;

import com.shanks.model.Category;
import com.shanks.model.Event;
import com.shanks.model.Food;
import com.shanks.model.Restaurant;
import com.shanks.request.CreateEventRequest;
import com.shanks.request.CreateFoodRequest;

import java.util.List;

public interface EventService {
    public Event createEvent(CreateEventRequest request ,Restaurant restaurant);

    public void deleteEvent(Long eventId) throws Exception;

    public List<Event> getRestaurantEvent(Long restaurantId);

    public Event findEventById(Long EventId) throws Exception;

    public List<Event> getAllEvents() throws Exception;

    public Event editEvent (Long id,CreateEventRequest request);

}
