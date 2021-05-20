package com.revature.shop.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
        if (itemName != null){
            this.itemName = itemName;}
        else this.itemName = "Revature Swag";
        if (price > 0){
            this.price = price;}
        else this.price = 0;
        if (quantity > 0){
            this.quantity = quantity;}
        else this.quantity = 0;
    }

    public StockItem() {

    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0){
        this.quantity = quantity;}
        else this.quantity = 0;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price > 0){
        this.price = price;}
        else this.price = 0;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        if (itemName != null){
        this.itemName = itemName;}
        else this.itemName = "Revature Swag";
    }
}
