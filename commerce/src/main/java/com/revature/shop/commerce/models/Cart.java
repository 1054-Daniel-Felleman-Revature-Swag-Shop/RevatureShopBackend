package com.revature.shop.commerce.models;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity(name = "cart")
public class Cart {

    @Id
    @GeneratedValue
    private int cartId;

    @Column(name = "my_shopper")
    private String myShopper;

    @Column(name = "stock_item")
    @ElementCollection
    private Map<String, Integer> stockItemMap;

    public Cart() {
    }

    public Cart(String myShopper, Map<String, Integer> stockItemMap) {
        this.myShopper = myShopper;
        this.stockItemMap = stockItemMap;
    }

    public Cart(int cartId, String myShopper, Map<String, Integer> stockItemMap) {
        this.cartId = cartId;
        this.myShopper = myShopper;
        this.stockItemMap = stockItemMap;
    }

    public int getCartId() {
        return cartId;
    }


    public String getMyShopper() {
        return myShopper;
    }

    public void setMyShopper(String myShopper) {
        this.myShopper = myShopper;
    }

    public Map<String, Integer> getStockItemMap() {
        return stockItemMap;
    }

    public void setStockItemMap(Map<String, Integer> stockItemMap) {
        this.stockItemMap = stockItemMap;
    }
}
