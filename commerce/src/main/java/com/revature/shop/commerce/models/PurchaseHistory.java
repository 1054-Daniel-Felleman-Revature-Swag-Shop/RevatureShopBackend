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

    @Column(name = "item_id")
    private Integer itemId;
    
    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_quantity")
    private Integer itemQuantity;

    @Column(name = "item_price")
    private Double itemPrice;

    @Column(name = "purchase_total")
    private Double purchaseTotal;

    public PurchaseHistory() {}

    public PurchaseHistory(String myShopper, String purchaseDate, Integer itemId, String itemName, Integer itemQuantity, Double itemPrice, Double thisItemTotal) {
        this.myShopper = myShopper;
        this.purchaseDate = purchaseDate;
        this.itemId = itemId;
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

    public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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

	public Double getPurchaseTotal() {
		return purchaseTotal;
	}

	public void setPurchaseTotal(Double purchaseTotal) {
		this.purchaseTotal = purchaseTotal;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemPrice == null) ? 0 : itemPrice.hashCode());
		result = prime * result + ((itemQuantity == null) ? 0 : itemQuantity.hashCode());
		result = prime * result + ((myShopper == null) ? 0 : myShopper.hashCode());
		result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
		result = prime * result + purchaseId;
		result = prime * result + ((purchaseTotal == null) ? 0 : purchaseTotal.hashCode());
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
		PurchaseHistory other = (PurchaseHistory) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemPrice == null) {
			if (other.itemPrice != null)
				return false;
		} else if (!itemPrice.equals(other.itemPrice))
			return false;
		if (itemQuantity == null) {
			if (other.itemQuantity != null)
				return false;
		} else if (!itemQuantity.equals(other.itemQuantity))
			return false;
		if (myShopper == null) {
			if (other.myShopper != null)
				return false;
		} else if (!myShopper.equals(other.myShopper))
			return false;
		if (purchaseDate == null) {
			if (other.purchaseDate != null)
				return false;
		} else if (!purchaseDate.equals(other.purchaseDate))
			return false;
		if (purchaseId != other.purchaseId)
			return false;
		if (purchaseTotal == null) {
			if (other.purchaseTotal != null)
				return false;
		} else if (!purchaseTotal.equals(other.purchaseTotal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PurchaseHistory [purchaseId=" + purchaseId + ", myShopper=" + myShopper + ", purchaseDate="
				+ purchaseDate + ", itemId=" + itemId + ", itemName=" + itemName + ", itemQuantity=" + itemQuantity
				+ ", itemPrice=" + itemPrice + ", purchaseTotal=" + purchaseTotal + "]";
	}
}
