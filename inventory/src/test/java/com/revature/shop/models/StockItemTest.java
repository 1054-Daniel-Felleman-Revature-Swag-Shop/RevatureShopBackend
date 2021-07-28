package com.revature.shop.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockItemTest {
    //What do I even test in a model? Its internal data validation maybe?
    //Yeah, that's what I'll test!
    public StockItem sItem;

    @Test
    public void testGetItemName(){
        sItem = new StockItem("Hat", 50, 1000,"accessories", "Sample hat", null, 0);
        assertEquals(sItem.getItemName(), "Hat");
    }


    @Test
    public void testGetItemNameNullString(){
        sItem = new StockItem(null, 10, 10, null, null, null, 0);
        assertEquals(sItem.getItemName(), "Revature Swag");

    }

    @Test
    public void testConstructorNegativeValues(){
        sItem = new StockItem("Nonsense", -1000, -1000, null, null, null, 0);
        assertEquals(sItem.getItemPrice(), 0);
        assertEquals(sItem.getQuantity(), 0);
    }

    @Test
    public void testSetPrice(){
        sItem = new StockItem("Hat", 10, 10, null, null, null, 0);
        sItem.setItemPrice(100);
        assertEquals(sItem.getItemPrice(), 100);

        sItem.setItemPrice(-1000);
        assertEquals(sItem.getItemPrice(), 0);
    }

    @Test
    public void testSetQuantity(){
        sItem = new StockItem("Hat", 10, 10, null, null, null, 0);
        sItem.setQuantity(100);
        assertEquals(sItem.getQuantity(), 100);

        sItem.setQuantity(-100);
        assertEquals(sItem.getQuantity(), 0);
    }
    
    @Test
    public void testSetSize() {
    	sItem = new StockItem("Shirt", 20, 100, null, null, "Small", 0);
    	assertEquals(sItem.getSize(), "Small");
    	
    	sItem.setSize("Large");
    	assertEquals(sItem.getSize(), "Large");
    }

    @Test
    public void testGetDiscount() {
    	sItem = new StockItem("Hat", 10, 10, null, null, null, -1);
    	assertEquals(sItem.getDiscount(), 0);
    	
    	sItem.setDiscount(-10);
    	assertEquals(sItem.getDiscount(), 0);
    }

}
