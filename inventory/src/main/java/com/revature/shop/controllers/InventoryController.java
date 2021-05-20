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
import java.util.List;


@RestController
@RequestMapping("/api/inventory")
//@CrossOrigin(origins = {"http://localhost:4200", "https://sessions.s3.us-east-2.amazonaws.com"},  allowCredentials = "true")
public class InventoryController
{
//    private final InventoryService inventoryService;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryController(InventoryRepository inventoryRepository)
    {
//        this.inventoryService = inventoryService;
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/view")
    public List<StockItem> getStockItems()
    {
        return inventoryRepository.findAll();
    }

    @PutMapping("/stockitem/new/{id}")
    public ResponseEntity<?> addNewItem(@PathVariable int id, HttpSession session)
    {
        StockItem newItem = (StockItem) session.getAttribute("itemName");
        inventoryRepository.save(newItem);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @RequestMapping(value = "/updatestockitem", method = RequestMethod.PATCH)
//    @PostMapping("/restockitem")
    @PatchMapping("/stockitem/update/{id}")
    public ResponseEntity<?> restockItem(@PathVariable int id, HttpSession session)
    {
        StockItem newItem = (StockItem) session.getAttribute("itemName");
        inventoryRepository.updateQuantity(newItem.getQuantity(), id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
