package com.revature.shop.controllers;


import com.revature.shop.models.StockItem;
import com.revature.shop.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/api/inventory")
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

//    @GetMapping("/view/category")
//    public ResponseEntity<?> getStockItemsCat(@RequestBody StockItem item){
//        List<StockItem> itemsList = inventoryService.getStockByCategory(item.getCategory());
//        if(itemsList == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(itemsList, HttpStatus.ACCEPTED);
//
//    }

    @PostMapping("/view/category")
    public ResponseEntity<?> getStockItemsCat(@RequestBody String category)
    {
        List<StockItem> itemsList = inventoryService.getStockByCategory(category);
        if(category == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemsList, HttpStatus.ACCEPTED);

    }

    @PutMapping(value = "/stockitem/new", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addNewItem(@RequestBody StockItem item)
    {
        System.out.println(item);
        //System.out.println("itemImage: "+itemImage);
        Integer idOfNewItem = inventoryService.addToStock(item, null);

        return new ResponseEntity<>(idOfNewItem, HttpStatus.CREATED);
    }

    //This says restock, but can be used to reduce quantity as well.
    @PutMapping("/stockitem/update/quantity")
    public ResponseEntity<?> restockItem(@RequestBody StockItem item)
    {
        boolean isChangedQuantity = inventoryService.updateStockItemQuantity(item.getItemName(), item.getQuantity());

        return new ResponseEntity<>(isChangedQuantity, HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/stockitem/update/addimage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<?> uploadItemImage(@RequestParam("id") Integer itemId, @RequestParam("image") MultipartFile imageFile) {

        System.out.println("HELLO uploadItemImage(): id = "+itemId);

        boolean uploadWorked = inventoryService.uploadImageForItemWithId(itemId, imageFile);

        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);

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
