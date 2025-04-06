package com.interfaces;

import java.util.List;

import com.model.OrderItems;

public interface OrderItemsDao {
	OrderItems addOrderItem(OrderItems orderItem);
    List<OrderItems> getOrderItemsByOrder(Long orderId);
    void deleteOrderItem(Long orderItemId);

}
