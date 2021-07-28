package com.revature.shop.commerce.dtos;

import java.util.Objects;

public class StockItemDto {

    private String myshopper;
    private String itemName;
    private int itemPrice;
    private int cartQuantity;
    private String category;
    private String description;

    public StockItemDto() {
    }

    public StockItemDto(String myshopper, String itemName, int itemPrice, int cartQuantity, String category, String description) {
        this.myshopper = myshopper;
        this.itemName = Objects.requireNonNullElse(itemName, "Revature Swag");
        this.itemPrice = Math.max(itemPrice, 0);
        this.cartQuantity = Math.max(cartQuantity, 0);
        this.category = Objects.requireNonNullElse(category, "Misc");
        this.description = Objects.requireNonNullElse(description, "No description provided.");
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

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
