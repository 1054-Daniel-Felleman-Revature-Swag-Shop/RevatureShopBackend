package com.revature.shop.services;

import com.revature.shop.models.StockItem;
import com.revature.shop.repositories.InventoryRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    @Mock
    InventoryRepository iRep = Mockito.mock(InventoryRepository.class);

    InventoryService iServ = new InventoryService(iRep);

    StockItem s1 = new StockItem("Hat", 100, 1000, "Accessory", "A sweet hat");
    StockItem s2 = new StockItem("Shirt", 1000, 100, "Clothing", "A cool shirt");
    StockItem s3 = new StockItem("Socks", 10, 10000, "Clothing", "Some nice socks");

    List<StockItem> sList = new ArrayList<StockItem>();

    @Before
    public void setUpTests(){
        sList.add(s1);
        sList.add(s2);
        sList.add(s3);

        Mockito.when(iRep.findAll()).thenReturn(sList);
        Mockito.when(iRep.findByQuantityEquals(0)).thenReturn(sList);
        Mockito.when(iRep.findByQuantityGreaterThan(0)).thenReturn(sList);
    }

//
//    @Test
//    public void testGetAll(){
//        assertEquals(iServ.getAllStock(), sList);
//    }
//
//    @Test
//    public void testGetInStock(){
//        assertEquals(iServ.getInStock(), sList);
//    }
//
//    @Test
//    public void testGetOutOfStock() {assertEquals(iServ.getOutOfStock(), sList);}

}