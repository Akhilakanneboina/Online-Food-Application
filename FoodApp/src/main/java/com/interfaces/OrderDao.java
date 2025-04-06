package com.interfaces;

import java.util.List;

import com.model.Orders;

public interface OrderDao {
	 Orders addOrder(Orders order);
	    Orders getOrder(Long orderId);
	    List<Orders> getOrdersByUser(Long userId);
	    void deleteOrder(Long orderId);

}
