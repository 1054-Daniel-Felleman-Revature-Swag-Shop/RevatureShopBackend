package com.revature.shop.commerce.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import java.util.Map;
import java.util.Set;

@Entity(name = "cart")
public class Cart {

    @Id
    @GeneratedValue
    private String cartId;

    @Column(name = "myshopper")
    private String myShopper;

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
