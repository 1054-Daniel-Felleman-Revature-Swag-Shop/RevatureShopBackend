package com.revature.shop.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="inventory")
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    @Column(name = "item_name")
    private String item_name;

    @Column(name = "item_price")
    private int item_price;

    private int quantity;

    public StockItem(String item_name, int item_price, int quantity) {
        this.item_name = Objects.requireNonNullElse(item_name, "Revature Swag");
        this.item_price = Math.max(item_price, 0);
        this.quantity = Math.max(quantity, 0);
    }

    public StockItem() {

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

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int price) {
        this.item_price = Math.max(price, 0);
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String itemName) {
        this.item_name = Objects.requireNonNullElse(itemName, "Revature Swag");
    }
}
