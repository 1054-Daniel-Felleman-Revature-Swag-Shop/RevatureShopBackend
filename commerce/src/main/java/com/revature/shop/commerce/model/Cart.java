package com.revature.shop.commerce.model;

import javax.persistence.*;
import java.util.Map;

@Entity(name = "cart")
public class Cart {

    @Id
    @GeneratedValue
    private String cartId;

    @Column(name = "my_shopper")
    private String myShopper;

    @Column(name = "stock_item")
    @ElementCollection
    private Map<String, Integer> stockItemMap;

    public Cart() {
    }

    public Cart(String cartId, String myShopper, Map<String, Integer> stockItemMap) {
        this.cartId = cartId;
        this.myShopper = myShopper;
        this.stockItemMap = stockItemMap;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
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
