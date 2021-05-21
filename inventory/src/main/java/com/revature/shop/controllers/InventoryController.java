package com.revature.shop.controllers;


import com.revature.shop.models.StockItem;
import com.revature.shop.repositories.InventoryRepository;
import com.revature.shop.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/inventory")
//@CrossOrigin(origins = {"http://localhost:4200", "https://sessions.s3.us-east-2.amazonaws.com"},  allowCredentials = "true")
public class InventoryController
{
    private final InventoryService inventoryService;
//    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryController(InventoryService inventoryService)
    {
        this.inventoryService = inventoryService;
//        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/view")
    public ResponseEntity<?> getStockItems(HttpSession session)
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

//        StockItem newItem = (StockItem) session.getAttribute("itemName");
        System.out.println(item.getId());
        boolean isAddedItem = inventoryService.addToStock(item);

        return new ResponseEntity<>(isAddedItem, HttpStatus.CREATED);
    }

//    @PostMapping("/stockitem/update/{id}")
    @PostMapping("/stockitem/update")
    public ResponseEntity<?> restockItem(@RequestBody StockItem item)
    {
//        StockItem newItem = (StockItem) session.getAttribute("itemName");
        //inventoryRepository.updateQuantity(newItem.getQuantity(), id);
        boolean isChangeItem = inventoryService.updateStockItem(item);

        return new ResponseEntity<>(isChangeItem, HttpStatus.ACCEPTED);
    }
}
