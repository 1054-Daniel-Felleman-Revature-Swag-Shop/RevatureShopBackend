package com.revature.shop.commerce.service;

import com.revature.shop.commerce.dto.CartDto;
import com.revature.shop.commerce.dto.StockItemDto;
import com.revature.shop.commerce.exception.*;
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
    public CartDto updateCart(StockItemDto stockItemDto) throws ItemOutOfStockException {
        StockItem stockItem = stockItemRepository.findByItemName(stockItemDto.getItemName());
        if (stockItem.getQuantity() > 0) {

            Cart cart = cartRepository.findOneByMyShopper(stockItemDto.getMyshopper());
            if(cart == null){
                cart = new Cart(stockItemDto.getMyshopper());
                cartRepository.save(cart);
            }

            if (cart.getStockItemMap().containsKey(stockItemDto.getItemName()))
                cart.getStockItemMap().put(stockItemDto.getItemName(), cart.getStockItemMap().get(stockItemDto.getItemName()) + 1);
            else cart.getStockItemMap().put(stockItemDto.getItemName(), 1);
            cartRepository.save(cart);
            List<StockItemDto> stockItemDtoList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry: cart.getStockItemMap().entrySet()) {
                StockItemDto stockItemDto1 = new StockItemDto();
                stockItemDto1.setItemName(entry.getKey());
                stockItemDto1.setCartQuantity(entry.getValue());
                stockItemDto1.setPrice(stockItemRepository.findByItemName(entry.getKey()).getPrice());
                stockItemDtoList.add(stockItemDto1);
            }
            return new CartDto(cart.getMyShopper(), stockItemDtoList);
        }
        else throw new ItemOutOfStockException();
    }

    public CartDto removeItemFromCart (StockItemDto stockItemDto) throws ItemNotInCartException {
        Cart cart = cartRepository.findOneByMyShopper(stockItemDto.getMyshopper());
        if(cart == null){ //shopper has no cart persisted
            cart = new Cart(stockItemDto.getMyshopper());
            cartRepository.save(cart);
        }
        List<StockItemDto> stockItemDtoList = new ArrayList<>();
        if (cart.getStockItemMap().containsKey(stockItemDto.getItemName())) {
            cart.getStockItemMap().put(stockItemDto.getItemName(), cart.getStockItemMap().get(stockItemDto.getItemName()) - 1);
            if (cart.getStockItemMap().get(stockItemDto.getItemName()) == 0)
                cart.getStockItemMap().remove(stockItemDto.getItemName());
            cartRepository.save(cart);
            for (Map.Entry<String, Integer> entry: cart.getStockItemMap().entrySet()) {
                StockItemDto stockItemDto1 = new StockItemDto();
                stockItemDto1.setItemName(entry.getKey());
                stockItemDto1.setCartQuantity(entry.getValue());
                stockItemDto1.setPrice(stockItemRepository.findByItemName(entry.getKey()).getPrice());
                stockItemDtoList.add(stockItemDto1);
            }
            return new CartDto(cart.getMyShopper(), stockItemDtoList);
        }
        else throw new ItemNotInCartException();
    }

    public Cart saveCart(Cart cart) throws UnableToSaveCartException {
        return cartRepository.save(cart);
    }

    public int checkoutCart(Cart cart) throws UnableToCheckoutException
    {
        // compute your purchase total points
        int currentPurchaseTotal = 0;
        for(String key : cart.getStockItemMap().keySet()){
            StockItem curStockItem = stockItemRepository.findByItemName(key);
            if(curStockItem != null) {
                currentPurchaseTotal += curStockItem.getPrice() * cart.getStockItemMap().get(key);
            }
        }

        // set the map to an empty map, save cart
        Map<String, Integer> emptyStockItemMap = new HashMap<String, Integer>();
        cart.setStockItemMap(emptyStockItemMap);
        cartRepository.save(cart);

        // return total points
        return currentPurchaseTotal;
    }
}
