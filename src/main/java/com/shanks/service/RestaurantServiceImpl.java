package com.shanks.service;

import com.shanks.dto.RestaurantDto;
import com.shanks.model.Address;
import com.shanks.model.Restaurant;
import com.shanks.model.User;
import com.shanks.repository.AdressRepository;
import com.shanks.repository.RestaurantRepository;
import com.shanks.repository.UserRepository;
import com.shanks.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class RestaurantServiceImpl implements RestaurantService{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address = adressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformtion(req.getContactInformtion());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);


        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRequest) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updateRequest.getCuisineType());
        }
        if(restaurant.getDescription()!=null)
            restaurant.setDescription(updateRequest.getDescription());
        if(restaurant.getName()!=null)
            restaurant.setName(updateRequest.getName());

        return restaurantRepository.save(restaurant);

    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(id);
        if(opt.isEmpty()){
            throw  new Exception("Restaurant not found with id "+id);
        }
        return  opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant=restaurantRepository.findByOwnerId(userId);
        if(restaurant==null){
            throw new Exception("restaurant not found with owner id "+userId);

        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        boolean isAlreadyFavorite = user.getFavorites().stream()
                .anyMatch(favorite -> favorite.getId().equals(restaurantId));

        RestaurantDto restaurantDto = null;
        if (isAlreadyFavorite) {
            user.getFavorites().removeIf(favorite -> favorite.getId().equals(restaurantId));
        } else {
            restaurantDto = new RestaurantDto();
            restaurantDto.setDescription(restaurant.getDescription());
            restaurantDto.setName(restaurant.getName());
            restaurantDto.setImages(restaurant.getImages());
            restaurantDto.setId(restaurant.getId());
            user.getFavorites().add(restaurantDto);
        }
        userRepository.save(user);

        return restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);

    }

}
