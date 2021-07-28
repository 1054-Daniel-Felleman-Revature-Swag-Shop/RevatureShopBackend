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
    
    private Double discount;

    public StockItem(String item_name, int itemPrice, int quantity, String category, String description, double discount) {
        this.itemName = Objects.requireNonNullElse(item_name, "Revature Swag");
        this.itemPrice = Math.max(itemPrice, 0);
        this.quantity = Math.max(quantity, 0);
        this.category = Objects.requireNonNullElse(category, "Misc");
        this.description = Objects.requireNonNullElse(description, "No description provided.");
        this.discount = Math.max(discount, 0);
    }

    public StockItem() {

    }

	public int getId() {
		return id;
	}

//	public void setId(int id) {
//		this.id = id;
//	}

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
		this.itemPrice = Math.max(itemPrice, 0);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = Math.max(quantity, 0);
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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = Math.max(discount, 0);
	}

	@Override
	public String toString() {
		return "StockItem [id=" + id + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", quantity=" + quantity
				+ ", category=" + category + ", description=" + description + ", discount=" + discount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(discount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + itemPrice;
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockItem other = (StockItem) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(discount) != Double.doubleToLongBits(other.discount))
			return false;
		if (id != other.id)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemPrice != other.itemPrice)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

    
    
    
}
