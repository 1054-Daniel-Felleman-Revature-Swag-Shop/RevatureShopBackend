package com.revature.shop.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="inventory")
public class StockItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "item_name", unique = true, nullable = false)
    private String itemName;

    @Column(name = "item_price")
    private int item_price;

    private int quantity;

    public StockItem(String item_name, int item_price, int quantity) {
        this.itemName = Objects.requireNonNullElse(item_name, "Revature Swag");
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String item_name) {
        this.itemName = item_name;
    }

    //    public void setItem_name(String item_name) {
//        this.item_name = Objects.requireNonNullElse(itemName, "Revature Swag");
//    }

    @Override
    public String toString() {
        return "StockItem{" +
                "id=" + id +
                ", item_name='" + itemName + '\'' +
                ", item_price=" + item_price +
                ", quantity=" + quantity +
                '}';
    }
}
