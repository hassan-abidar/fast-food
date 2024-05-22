package com.shanks.service;

import com.shanks.model.Event;
import com.shanks.model.Restaurant;
import com.shanks.repository.EventRepository;
import com.shanks.repository.RestaurantRepository;
import com.shanks.request.CreateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;
    private RestaurantRepository repository;
    @Override
    public Event createEvent(CreateEventRequest request,Restaurant restaurant) {

        Event event = new Event();
            event.setName(request.getName());
            event.setImage(request.getImage());
            event.setDescription(request.getDescription());
            event.setLocation(request.getLocation());
            event.setRestaurant(restaurant);
            event.setStartedAt(request.getStartedAt());
            event.setEndsAt(request.getEndsAt());
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) throws Exception {
            try{
                Event event = eventRepository.findById(eventId).get();
                eventRepository.delete(event);
            }catch (Exception e){
                System.out.println(e);
            }
    }

    @Override
    public List<Event> getRestaurantEvent(Long restaurantId) {

        List<Event> events = eventRepository.findByRestaurantId(restaurantId);
        return events;
    }


    @Override
    public Event findEventById(Long EventId) throws Exception {

        Optional<Event> event = eventRepository.findById(EventId);
        return  event.get();
    }

    @Override
    public List<Event> getAllEvents() throws Exception {
        List<Event> events = eventRepository.findAll();

        return events ;
    }

    @Override
    public Event editEvent(Long id,CreateEventRequest request) {
        Event event = eventRepository.findById(id).get();
        event.setName(request.getName());
        event.setImage(request.getImage());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());
        event.setStartedAt(request.getStartedAt());
        event.setEndsAt(request.getEndsAt());
        return eventRepository.save(event);
    }

}
