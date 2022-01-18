package com.ecommerce.order.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
@DynamoDbBean
public class OrderProduct {

	private String code;
	private String name;
	private String price;
	private Integer quantity;
	private Integer totalPrice;

	public OrderProduct() {
	}

	public OrderProduct(String code, String name, String price, Integer quantity, Integer totalPrice) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	@DynamoDbSortKey
	@DynamoDbAttribute(value = "SK")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@DynamoDbPartitionKey
	@DynamoDbAttribute(value = "PK")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDbAttribute(value = "price")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@DynamoDbAttribute(value = "quantity")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@DynamoDbAttribute(value = "totalPrice")
	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OrderProduct [code=" + code + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + "]";
	}

}
