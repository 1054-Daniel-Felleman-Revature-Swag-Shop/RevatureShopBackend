package com.revature.shop.controllers;

import com.revature.shop.models.StockItem;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

public class e2eInventoryTesting {

	RestTemplate restTemplate = new RestTemplate();

	private final static URI uriView = URI.create("http://localhost:9001/inventoryms/api/inventory/view");

	@SuppressWarnings("rawtypes")
	@Test
	public void viewItemsE2E() {

		ResponseEntity<List> stockItemsList = restTemplate.getForEntity(uriView, List.class);
		assertTrue(stockItemsList.getBody().size() == 30);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void getAllCategoriesE2E() {
		URI uri = URI.create("http://localhost:9001/inventoryms/api/inventory/view/getallcategories");
		ResponseEntity<List> categoriesList = restTemplate.getForEntity(uri, List.class);
		assertTrue(categoriesList.getBody().size() == 8);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void viewByCategoryE2E() {
		String itemsCategory = "Misc";
		URI uri = URI.create("http://localhost:9001/inventoryms/api/inventory/view/itemsbycategory");
		ResponseEntity<List> stockItemsList = restTemplate.postForEntity(uri, itemsCategory, List.class);

		assertTrue(stockItemsList.getBody().size() == 5);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void addItemE2E() {
		StockItem item = new StockItem("Revature Smart Watch", 70, 90, "Misc", "Made of platinum, nuff said.", 0);
		URI uri = URI.create("http://localhost:9001/inventoryms/api/inventory/stockitem/new");
		restTemplate.put(uri, item);

		ResponseEntity<List> stockItemsList = restTemplate.getForEntity(uriView, List.class);
		assertEquals(31, stockItemsList.getBody().size());
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void updateQuantitiesE2E() {
		StockItem item = new StockItem("Rev It Up Hat", 50, 3500, "Misc", "A sweet hat to ACCELERATE your development!",
				0);
		URI uri = URI.create("http://localhost:9001/inventoryms/api/inventory/stockitem/update/quantity");
		restTemplate.put(uri, item);

		ResponseEntity<List> stockItemsList = restTemplate.getForEntity(uriView, List.class);
	}
}
