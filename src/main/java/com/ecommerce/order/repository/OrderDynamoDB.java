package com.ecommerce.order.repository;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.models.OrderDTO;
import com.ecommerce.products.exception.AddItemException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Repository;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

@Repository
public class OrderDynamoDB {
	private final static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;

	@Value("${cloud.aws.end-point.uri}")
	private String endpoint;

	@Autowired
	private DynamoDbEnhancedClient dynamoDbenhancedClient;

	public void addOrder(OrderDTO orderDTO) {
		try {
			DynamoDbTable<Order> table = getTable();

			Order order = new Order(orderDTO.getUserEmail(), orderDTO.getOrderID(), orderDTO.getPaymentMethod(),
					orderDTO.getAddress(), orderDTO.getProducts(), orderDTO.getTotalAmount());
			queueMessagingTemplate.convertAndSend(endpoint, orderDTO);
			table.putItem(order);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getStackTrace().toString());
			LOGGER.error("Error while adding product to table %s", e.getMessage());
			throw new AddItemException();
		}
		return;
	}

	public void deleteOrder(String orderId, String userEmail) {
		// TODO Auto-generated method stub
		DynamoDbTable<Order> orderTable = getTable();

		Key key = Key.builder().partitionValue(userEmail).sortValue(orderId).build();

		DeleteItemEnhancedRequest deleteRequest = DeleteItemEnhancedRequest.builder().key(key).build();

		orderTable.deleteItem(deleteRequest);
	}

	public List<OrderDTO> getAllOrdersByEmail(String emailId) {
		// TODO Auto-generated method stub
		DynamoDbTable<Order> productTable = getTable();

		Key key = Key.builder().partitionValue(emailId).build();

		QueryEnhancedRequest enhancedRequest = QueryEnhancedRequest.builder()
				.queryConditional(QueryConditional.keyEqualTo(key)).build();

		PageIterable<Order> orderIteratble = productTable.query(enhancedRequest);

		Iterator<Order> orders = orderIteratble.items().stream().collect(Collectors.toList()).iterator();
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();

		while (orders.hasNext()) {
			OrderDTO orderDTO = new OrderDTO();
			Order product = orders.next();
			BeanUtils.copyProperties(product, orderDTO);
			orderDTOs.add(orderDTO);
		}
		return orderDTOs;
	}

	private DynamoDbTable<Order> getTable() {
		DynamoDbTable<Order> table = dynamoDbenhancedClient.table("order", TableSchema.fromBean(Order.class));
		return table;
	}

}
