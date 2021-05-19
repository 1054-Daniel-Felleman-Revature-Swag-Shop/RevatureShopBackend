package com.revature.shop.commerce.dto;

public class StockItemDto {

    private String myshopper;
    private String itemName;
    private int price;
    private int quantity;
    private int cartQuantity;

    public String getMyshopper() {
        return myshopper;
    }

    public void setMyshopper(String myshopper) {
        this.myshopper = myshopper;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}
