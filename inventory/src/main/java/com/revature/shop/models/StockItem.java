package com.revature.shop.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class StockItem {

    @Id
    @GeneratedValue
    private int itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private int price;

    private int quantity;

    public StockItem(String itemName, int price, int quantity) {
        this.itemName = Objects.requireNonNullElse(itemName, "Revature Swag");
        this.price = Math.max(price, 0);
        this.quantity = Math.max(quantity, 0);
    }

    public StockItem() {

    }

    public int getItemId(){
        return this.itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.max(quantity, 0);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = Math.max(price, 0);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = Objects.requireNonNullElse(itemName, "Revature Swag");
    }
}
