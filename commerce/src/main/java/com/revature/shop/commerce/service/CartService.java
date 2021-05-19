package com.revature.shop.commerce.service;

import com.revature.shop.commerce.dto.CartDto;
import com.revature.shop.commerce.dto.StockItemDto;
import com.revature.shop.commerce.model.Cart;
import com.revature.shop.commerce.model.StockItem;
import com.revature.shop.commerce.repository.CartRepository;
import com.revature.shop.commerce.repository.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    StockItemRepository stockItemRepository;

    //Cart should be cleared after certain period of inactivity to release the items;
    public CartDto updateCart(StockItemDto stockItemDto) {
        Cart cart = cartRepository.findOneByMyshopper(stockItemDto.getMyshopper());
        if (cart.getStockItemMap().containsKey(stockItemDto.getItemName())) {
            int quantity = cart.getStockItemMap().get(stockItemDto.getItemName());
            cart.getStockItemMap().put(stockItemDto.getItemName(), ++quantity);
            //A method that allows us to decreament the items in the stock Repository
        }
        else cart.getStockItemMap().put(stockItemDto.getItemName(), 1);
        List<StockItemDto> stockItemDtoList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: cart.getStockItemMap().entrySet()) {
            StockItemDto stockItemDto1 = new StockItemDto();
            stockItemDto1.setItemName(entry.getKey());
            stockItemDto1.setCartQuantity(entry.getValue());
            stockItemDto1.setPrice(stockItemRepository.findByName(entry.getKey()).getPrice());
            stockItemDtoList.add(stockItemDto1);
        }
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getCartId());
        cartDto.setStockItemDtoList(stockItemDtoList);
        return cartDto;
    }

}
