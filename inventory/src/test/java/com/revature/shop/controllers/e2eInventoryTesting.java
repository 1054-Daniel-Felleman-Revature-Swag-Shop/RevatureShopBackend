package com.revature.shop.controllers;

import com.revature.shop.models.StockItem;
import org.junit.jupiter.api.Test;
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
        URI uri = URI.create("http://localhost:8088/api/inventory/view");
        ResponseEntity<List> stockItemsList = restTemplate.getForEntity(uri, List.class);
        stockItemsList.getStatusCode();
        stockItemsList.getBody();
    }

    @Test
    public void addItemE2E()
    {
        StockItem item = new StockItem("shorts", 50, 10);
//        restTemplate.
        URI uri = URI.create("http://localhost:8088/api/inventory/stockitem/new");
        restTemplate.put(uri, StockItem.class);
//        stockItemsList.getStatusCode();
//        stockItemsList.getBody();
    }

    @Test
    public void updateQuantitiesE2E()
    {
        URI uri = URI.create("http://localhost:8088/api/inventory/stockitem/update/1");
        ResponseEntity<StockItem> stockItemsList = restTemplate.getForEntity(uri,  StockItem.class);
        stockItemsList.getStatusCode();
        stockItemsList.getBody();
    }

}
