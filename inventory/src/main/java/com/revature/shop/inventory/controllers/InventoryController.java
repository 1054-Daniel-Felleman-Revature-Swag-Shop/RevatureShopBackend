package com.revature.shop.inventory.controllers;


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

    public InventoryController()
    {
        // todo
    }

    @GetMapping("/view")
    public List<StockItem> getStockItems()
    {
        //todo
    }

    @PutMapping("/stockitem/new/{id}")
    public boolean restockItems(int id)
    {
        //todo
    }

//    @RequestMapping(value = "/updatestockitem", method = RequestMethod.PATCH)
//    @PostMapping("/restockitem")
    @PatchMapping("/stockitem/update/{id}")
    public ResponseEntity<?> restockItems(@PathVariable int id, HttpSession session)
    {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
