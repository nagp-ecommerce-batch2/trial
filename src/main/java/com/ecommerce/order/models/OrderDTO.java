package com.ecommerce.order.models;

import java.util.List;

import com.ecommerce.order.entity.OrderProduct;


public class OrderDTO {
	private String userEmail;
	private String orderID;
	private String paymentMethod;
	private String address;
	private List<OrderProduct> products;
	private Float totalAmount;

	public OrderDTO() {
	}

	public OrderDTO(String userEmail, String orderID, String paymentMethod, String address, List<OrderProduct> products,
			Float totalAmount) {
		this.userEmail = userEmail;
		this.orderID = orderID;
		this.paymentMethod = paymentMethod;
		this.address = address;
		this.products = products;
		this.totalAmount = totalAmount;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OrderProduct> getProducts() {
		return products;
	}

	public void setProducts(List<OrderProduct> products) {
		this.products = products;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Order [address=" + address + ", orderID=" + orderID + ", paymentMethod=" + paymentMethod + ", products="
				+ products + ", totalAmount=" + totalAmount + ", userEmail=" + userEmail + "]";
	}
}
