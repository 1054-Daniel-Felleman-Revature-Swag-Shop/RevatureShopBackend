package com.revature.shop.controllers;


import com.revature.shop.models.StockItem;
import com.revature.shop.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:4200")
public class InventoryController
{
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService)
    {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/view")
    public ResponseEntity<?> getStockItems()
    {
        List<StockItem> itemsList = inventoryService.getAllStock();
        if(itemsList == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(itemsList, HttpStatus.ACCEPTED);
    }

    @PutMapping("/stockitem/new")
    public ResponseEntity<?> addNewItem(@RequestBody StockItem item)
    {
        System.out.println(item);
        boolean isAddedItem = inventoryService.addToStock(item);

        return new ResponseEntity<>(isAddedItem, HttpStatus.CREATED);
    }

    @PutMapping("/stockitem/update/quantity")
    public ResponseEntity<?> restockItem(@RequestBody StockItem item)
    {
        boolean isChangedQuantity = inventoryService.updateStockItemQuantity(item.getItemName(), item.getQuantity());

        return new ResponseEntity<>(isChangedQuantity, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/item/name")
    public StockItem getItemByName (@RequestParam String itemName) {
        return inventoryService.getItemByName(itemName);
    }

    @DeleteMapping("/delete/item/name")
    public void deleteItemByName (@RequestParam String itemName) {
        inventoryService.deleteItemByName(itemName);
    }

}
