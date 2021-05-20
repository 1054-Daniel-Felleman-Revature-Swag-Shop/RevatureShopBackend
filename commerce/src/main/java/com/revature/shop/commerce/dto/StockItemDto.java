package com.revature.shop.commerce.dto;

public class StockItemDto {

    private String myshopper;
    private String itemName;
    private int price;
    private int cartQuantity;

    public StockItemDto() {
    }

    public StockItemDto(String myshopper, String itemName, int price, int cartQuantity) {
        this.myshopper = myshopper;
        this.itemName = itemName;
        this.price = price;
        this.cartQuantity = cartQuantity;
    }

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

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}
