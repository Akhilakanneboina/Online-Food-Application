package com.interfaces;

import java.util.List;

import com.model.Menu;

public interface MenuDao {
	 Menu addMenu(Menu menu);
	    Menu getMenu(Long menuId);
	    Menu updateMenu(Long menuId, Menu menu);
	    void deleteMenu(Long menuId);
	    List<Menu> getMenuByRestaurant(Long restaurantId);

}
