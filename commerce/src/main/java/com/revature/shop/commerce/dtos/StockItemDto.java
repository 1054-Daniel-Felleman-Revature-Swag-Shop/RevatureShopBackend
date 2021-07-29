package com.revature.shop.commerce.dtos;

import java.util.Objects;

public class StockItemDto {

    private String myshopper;
    private int id;
    private String itemName;
    private int itemPrice;
    private int cartQuantity;
    private String category;
    private String description;
    private String size;

    public StockItemDto() {
    }

    public StockItemDto(String myshopper, int id, String itemName, int itemPrice, int cartQuantity, String category, String description, String size) {
        this.myshopper = myshopper;
        this.id = Math.max(id, 1);
        this.itemName = Objects.requireNonNullElse(itemName, "Revature Swag");
        this.itemPrice = Math.max(itemPrice, 0);
        this.cartQuantity = Math.max(cartQuantity, 0);
        this.category = Objects.requireNonNullElse(category, "Misc");
        this.description = Objects.requireNonNullElse(description, "No description provided.");
        this.size = Objects.requireNonNullElse(size, "No size");
    }
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

    public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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
