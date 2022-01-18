package com.ecommerce.order.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.models.OrderDTO;
import com.ecommerce.order.repository.OrderDynamoDB;
import com.ecommerce.order.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
	OrderDynamoDB orderDynamoDB;
	@Override
	public void addOrder(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		orderDynamoDB.addOrder(orderDTO);
	}
	@Override
	public void deleteOrder(String orderId,String userEmail) {
		// TODO Auto-generated method stub
		orderDynamoDB.deleteOrder(orderId,userEmail);
	}
	@Override
	public List<OrderDTO> getAllOrdersByEmail(String emailId) {
		// TODO Auto-generated method stub
		return orderDynamoDB.getAllOrdersByEmail(emailId);
	}

}
