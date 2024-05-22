package com.shanks.controller;

import com.shanks.model.Event;
import com.shanks.model.Restaurant;
import com.shanks.model.User;
import com.shanks.request.CreateEventRequest;
import com.shanks.response.MessageResponse;
import com.shanks.service.EventService;
import com.shanks.service.RestaurantService;
import com.shanks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/event")

public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody CreateEventRequest request,
                                            @RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
        Event event = eventService.createEvent(request,restaurant);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponse> deleteEvent(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        eventService.deleteEvent(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("Event deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<Event>> getAllEvents(@RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
         List <Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    @GetMapping("{id}")
        public ResponseEntity<List<Event>> getRestaurantEvents(@PathVariable Long id,
                                                               @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Event> events = eventService.getRestaurantEvent(id);
        return new ResponseEntity<>(events,HttpStatus.OK);
        }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Event> editEvent(@RequestBody CreateEventRequest request,@PathVariable Long id,
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        Event event = eventService.editEvent(id,request);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }
}
