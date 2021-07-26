package com.revature.shop.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="inventory")
public class StockItem
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_name", unique = true, nullable = false)
    private String itemName;

    @Column(name = "item_price")
    private int itemPrice;

    private int quantity;

    private String category;

    private String description;
    
    private String size;

    public StockItem() {
		super();
	}

	public StockItem(String item_name, int itemPrice, int quantity, String category, String description, String size) {
        this.itemName = Objects.requireNonNullElse(item_name, "Revature Swag");
        this.itemPrice = Math.max(itemPrice, 0);
        this.quantity = Math.max(quantity, 0);
        this.category = Objects.requireNonNullElse(category, "Misc");
        this.description = Objects.requireNonNullElse(description, "No description provided.");
        this.size = Objects.requireNonNullElse(size, "No size");
    }

    public int getId(){
        return this.id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.max(quantity, 0);
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int price) {
        this.itemPrice = Math.max(price, 0);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String item_name) {
        this.itemName = item_name;
    }
    
    public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "StockItem [id=" + id + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", quantity=" + quantity
				+ ", category=" + category + ", description=" + description + ", size=" + size + "]";
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNullElse(description, "No description provided.");
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = Objects.requireNonNullElse(category, "Misc");
    }

}
