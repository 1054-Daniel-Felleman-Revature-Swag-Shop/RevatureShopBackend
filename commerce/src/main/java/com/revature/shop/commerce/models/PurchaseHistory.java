package com.revature.shop.commerce.models;

import javax.persistence.*;

@Entity(name = "purchase_history")
public class PurchaseHistory {

    @Id
    @GeneratedValue
    private int purchaseId;

    @Column(name = "my_shopper")
    private String myShopper;

    @Column(name = "purchase_date")
    private String purchaseDate;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_quantity")
    private Integer itemQuantity;

    @Column(name = "item_price")
    private Double itemPrice;

    @Column(name = "purchase_total")
    private Double purchaseTotal;


    public PurchaseHistory() {
    }

    public PurchaseHistory(String myShopper, String purchaseDate, String itemName, Integer itemQuantity, Double itemPrice, double thisItemTotal) {

        this.myShopper = myShopper;
        this.purchaseDate = purchaseDate;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.purchaseTotal = thisItemTotal;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getMyShopper() {
        return myShopper;
    }

    public void setMyShopper(String myShopper) {
        this.myShopper = myShopper;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Double getPurchaseAmount() {
        return purchaseTotal;
    }

    public void setPurchaseAmount(Double purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }

    @Override
    public String toString() {
        return "PurchaseHistory{" +
                "purchaseId=" + purchaseId +
                ", myShopper='" + myShopper + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", itemPrice=" + itemPrice +
                ", purchaseTotal=" + purchaseTotal +
                '}';
    }
}
