package com.ecommerce.order.entity;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderProductConverter implements DynamoDBTypeConverter<Map<String, String>, OrderProduct> {

	@Override
	public Map<String, String> convert(OrderProduct orderProduct) {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();

		Map<String, String> map = objectMapper.convertValue(orderProduct, Map.class);
		return map;
	}

	@Override
	public OrderProduct unconvert(Map<String, String> props) {
		// TODO Auto-generated method stub
		OrderProduct orderProduct = new OrderProduct();

		for (String key : props.keySet()) {
			String value = props.get(key);
			switch (key) {
			case "PK": {
				orderProduct.setName(value);
				break;
			}
			case "SK": {
				orderProduct.setCode(value);
				break;
			}
			case "price": {
				orderProduct.setPrice(Integer.parseInt(value));
				;
				break;
			}
			case "quantity": {
				orderProduct.setQuantity(Integer.parseInt(value));
				;
				break;
			}
			case "totalPrice": {
				orderProduct.setTotalPrice(Integer.parseInt(value));
				;
				break;
			}
			}
		}

		return orderProduct;
	}

}
