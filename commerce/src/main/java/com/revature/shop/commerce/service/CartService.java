package com.revature.shop.commerce.service;

import com.revature.shop.commerce.dto.CartDto;
import com.revature.shop.commerce.dto.StockItemDto;
import com.revature.shop.commerce.exception.ItemOutOfStockException;
import com.revature.shop.commerce.exception.UnableToSaveCartException;
import com.revature.shop.commerce.model.Cart;
import com.revature.shop.commerce.model.StockItem;
import com.revature.shop.commerce.repository.CartRepository;
import com.revature.shop.commerce.repository.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            if (cart.getStockItemMap().containsKey(stockItemDto.getItemName()))
                cart.getStockItemMap().put(stockItemDto.getItemName(), cart.getStockItemMap().get(stockItemDto.getItemName()) + 1);
            else cart.getStockItemMap().put(stockItemDto.getItemName(), 1);
//            stockItem.setQuantity(stockItem.getQuantity() - 1);
//            stockItemRepository.save(stockItem);
            cartRepository.save(cart);
            List<StockItemDto> stockItemDtoList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry: cart.getStockItemMap().entrySet()) {
                StockItemDto stockItemDto1 = new StockItemDto();
                stockItemDto1.setItemName(entry.getKey());
                stockItemDto1.setCartQuantity(entry.getValue());
                stockItemDto1.setPrice(stockItemRepository.findByItemName(entry.getKey()).getPrice());
                stockItemDtoList.add(stockItemDto1);
            }
            CartDto cartDto = new CartDto();
            cartDto.setMyShopper(cart.getMyShopper());
            cartDto.setStockItemDtoList(stockItemDtoList);
            return cartDto;
        }
        else throw new ItemOutOfStockException();
    }

    public Cart saveCart(Cart cart) throws UnableToSaveCartException {
        return cartRepository.save(cart);
    }
}
