package com.ecommerce.order.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class OrderProduct {

	private String code;
	private String name;
	private Integer price;
	private Integer quantity;
	private Integer totalPrice;
	private String size;

	public OrderProduct() {
	}

	public OrderProduct(String code, String name, Integer price, Integer quantity, Integer totalPrice, String size) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.size = size;
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
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
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
	@DynamoDbAttribute(value = "size")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "OrderProduct [code=" + code + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + ", size=" + size + "]";
	}

}
