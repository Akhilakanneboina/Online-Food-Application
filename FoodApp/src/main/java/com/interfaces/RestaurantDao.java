package com.interfaces;
import java.util.List;

import com.model.Restaurant;


public interface RestaurantDao {
	
    Restaurant getRestaurant(Long restaurantId);
    Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant);
    void deleteRestaurant(Long restaurantId);
    List<Restaurant> getAllRestaurants();
	Restaurant addRestaurant(Restaurant restaurant);
	
	

}
