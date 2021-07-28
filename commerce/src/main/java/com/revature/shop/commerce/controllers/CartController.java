package com.revature.shop.commerce.controllers;

import com.revature.shop.commerce.dtos.StockItemDto;
import com.revature.shop.commerce.exceptions.*;
import com.revature.shop.commerce.models.Cart;
import com.revature.shop.commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;


@RestController
@ComponentScan(basePackages = "com.revature.shop.commerce")
@RequestMapping(value = "/commerce")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping(value = "/welcomeToCommerce")
    public void welcomeToCommerce(){
        //return the logged in user's cart
        System.out.println("Welcome to commerce.");
    }

    @PutMapping(value = "/addtocart")
    public ResponseEntity<?> updateCart (@RequestBody StockItemDto stockItemDto) {
        try {
            return new ResponseEntity<>(cartService.updateCart(stockItemDto), HttpStatus.OK);
        } catch (ItemOutOfStockException e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(value = "/removefromcart")
    public ResponseEntity<?> removeItemFromCart (@RequestBody StockItemDto stockItemDto) {
        try {
            return new ResponseEntity<>(cartService.removeItemFromCart(stockItemDto), HttpStatus.OK);
        } catch (ItemNotInCartException e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(value = "/savecart")
    public ResponseEntity<?> saveCart(@RequestBody Cart cart) {
        try {
            return new ResponseEntity<>(cartService.saveCart(cart), HttpStatus.ACCEPTED);
        } catch (UnableToSaveCartException e){
            e.printStackTrace();
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(value = "/checkoutcart")
    public ResponseEntity<?> checkoutCart(@RequestBody Cart cart){
        try{
            return new ResponseEntity<>(cartService.checkoutCart(cart), HttpStatus.ACCEPTED);
        } catch (UnableToCheckoutException e){
            e.printStackTrace();
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/myCart/{shopper}")
    public ResponseEntity<?> getShopperCart(@PathVariable String shopper){
        return new ResponseEntity<>(cartService.getShopperCart(shopper), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/myOrderHistory/{shopper}")
    public ResponseEntity<?> getMyOrderHistory(@PathVariable String shopper){
        return new ResponseEntity<>(cartService.getShoppoingHistory(shopper), HttpStatus.ACCEPTED);
    }

}
