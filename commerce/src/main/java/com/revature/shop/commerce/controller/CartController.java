package com.revature.shop.commerce.controller;

import com.revature.shop.commerce.dto.StockItemDto;
import com.revature.shop.commerce.model.Cart;
import com.revature.shop.commerce.exception.*;
import com.revature.shop.commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/commerce")
public class CartController {

    @Autowired
    CartService cartService;
    
    @PutMapping(value = "/addtocart")
    public ResponseEntity<?> updateCart (@RequestBody StockItemDto stockItemDto) {
        try {
            return new ResponseEntity<>(cartService.updateCart(stockItemDto), HttpStatus.OK);
        } catch (ItemOutOfStockException e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(value = "/saveCart")
    public ResponseEntity<Cart> saveCart(@RequestBody Cart cart) {
        try {
            return new ResponseEntity<Cart>(cartService.saveCart(cart), HttpStatus.OK);
        } catch (UnableToSaveCartException e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


}
