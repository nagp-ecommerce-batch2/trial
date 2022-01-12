package com.ecommerce.order.entity;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

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
