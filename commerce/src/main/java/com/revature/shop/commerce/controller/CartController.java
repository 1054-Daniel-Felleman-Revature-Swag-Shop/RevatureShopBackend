package com.revature.shop.commerce.controller;

import com.revature.shop.commerce.dto.CartDto;
import com.revature.shop.commerce.dto.StockItemDto;
import com.revature.shop.commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/commerce")
public class CartController {

    @Autowired
    CartService cartService;
    
    @PutMapping(value = "/addtocart")
    public CartDto updateCart (@RequestBody StockItemDto stockItemDto) {
        return cartService.updateCart(stockItemDto);
    }


}
