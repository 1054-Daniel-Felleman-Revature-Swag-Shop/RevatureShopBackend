package com.revature.shop.controllers;

import com.revature.shop.models.StockItem;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

public class e2eInventoryTesting
{

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void viewItemsE2E()
    {
        URI uri = URI.create("http://localhost:9001/inventoryms/api/inventory/view");
        ResponseEntity<List> stockItemsList = restTemplate.getForEntity(uri, List.class);
        System.out.println(stockItemsList.getBody());
        assertTrue(stockItemsList.getBody().size() == 30);
    }

    @Test
    public void getAllCategoriesE2E()
    {
        URI uri = URI.create("http://localhost:9001/inventoryms/api/inventory/view/getallcategories");
        ResponseEntity<List> categoriesList = restTemplate.getForEntity(uri, List.class);
        System.out.println(categoriesList.getBody());
        assertTrue(categoriesList.getBody().size() == 8);
    }

    @Test
    public void viewByCategoryE2E()
    {
        String itemsCategory = "Misc";
        URI uri = URI.create("http://localhost:9001/inventoryms/api/inventory/view/itemsbycategory");
        ResponseEntity<List> stockItemsList = restTemplate.postForEntity(uri, itemsCategory, List.class);

        System.out.println(stockItemsList.getBody().toString());
        System.out.println(stockItemsList.getBody().size());
        assertTrue(stockItemsList.getBody().size() == 5);
    }

    @Test
    public void addItemE2E()
    {
        StockItem item = new StockItem("Revature Smart Watch", 70, 90, "Misc", "Made of platinum, nuff said.");
        URI uri = URI.create("http://localhost:9001/inventoryms/api/inventory/stockitem/new");
        restTemplate.put(uri, item);

        URI uri2 = URI.create("http://localhost:9001/inventoryms/api/inventory/view");
        ResponseEntity<List> stockItemsList = restTemplate.getForEntity(uri2, List.class);
        System.out.println(stockItemsList.getBody().size());
        assertEquals(31, stockItemsList.getBody().size());
    }

    @Test
    public void updateQuantitiesE2E()
    {
        StockItem item = new StockItem("Rev It Up Hat", 50, 3500, "Misc", "A sweet hat to ACCELERATE your development!");
        URI uri = URI.create("http://localhost:9001/inventoryms/api/inventory/stockitem/update/quantity");
        restTemplate.put(uri, item);

        URI uri2 = URI.create("http://localhost:9001/inventoryms/api/inventory/view");
        ResponseEntity<List> stockItemsList = restTemplate.getForEntity(uri2, List.class);
        System.out.println(stockItemsList.getBody().get(0));

        //assert update quantities is doing what we expect
        //stockItemsList.getBody()
    }
}
