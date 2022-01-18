package com.ecommerce.order.service;

import java.util.List;

import com.ecommerce.order.models.OrderDTO;

public interface OrderService {

	public void addOrder(OrderDTO orderDTO);

	public void deleteOrder(String orderId,String userEmail);

	public List<OrderDTO> getAllOrdersByEmail(String emailId);

}
