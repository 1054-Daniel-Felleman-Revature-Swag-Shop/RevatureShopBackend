package com.revature.shop.commerce.service;

import com.revature.shop.commerce.model.*;
import com.revature.shop.commerce.dto.*;
import com.revature.shop.commerce.exception.*;
import com.revature.shop.commerce.repository.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceUnitTests {
    @InjectMocks
    com.revature.shop.commerce.service.CartService mockedCartService;

    @Mock
    CartRepository cartRepository;

    @Mock
    StockItemRepository stockItemRepository;

    @Test
    public void updateCart() throws ItemOutOfStockException {
        Cart cart = new Cart(1, "abdulmoeedak", new HashMap<String, Integer>(){{
            put("t-shirt",1);
        }});
        StockItem stockItem = new StockItem(1, "cup", 10, 10);
        StockItem stockItem2 = new StockItem(1, "t-shirt", 15, 20);
        when(stockItemRepository.findByItemName("cup")).thenReturn(stockItem);
        when(stockItemRepository.findByItemName("t-shirt")).thenReturn(stockItem2);
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        StockItemDto stockItemDto = new StockItemDto("abdulmoeedak", "cup", 10, 1);

        CartDto cartDto = mockedCartService.updateCart(stockItemDto);
        assertEquals(2, cartDto.getStockItemDtoList().size());
        cart = new Cart(1, "abdulmoeedak", new HashMap<String, Integer>(){{
            put("t-shirt",1);
            put("cup",1);
        }});
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        cartDto = mockedCartService.updateCart(stockItemDto);
        assertEquals(2, cartDto.getStockItemDtoList().size());
        assertTrue(cartDto.getStockItemDtoList().stream().anyMatch(stDto -> stDto.getItemName().equals("cup") && stDto.getCartQuantity() == 2));
        stockItem = new StockItem(1, "cup", 10, 0);
        when(stockItemRepository.findByItemName("cup")).thenReturn(stockItem);
        Exception exception = assertThrows(ItemOutOfStockException.class, () -> mockedCartService.updateCart(stockItemDto));
        assertTrue(exception.getMessage().equals("Item Out Of Stock"));
    }

    @Test
    public void removeItemFromCart () throws ItemNotInCartException {
        Cart cart = new Cart(1, "abdulmoeedak", new HashMap<String, Integer>(){{
            put("t-shirt",1);
            put("cup",1);
        }});
        StockItemDto stockItemDto = new StockItemDto("abdulmoeedak", "t-shirt", 10, 1);
        StockItem stockItem = new StockItem(1, "cup", 10, 10);
        StockItem stockItem2 = new StockItem(1, "t-shirt", 15, 20);
        when(stockItemRepository.findByItemName("cup")).thenReturn(stockItem);
        when(stockItemRepository.findByItemName("t-shirt")).thenReturn(stockItem2);
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        CartDto cartDto = mockedCartService.removeItemFromCart(stockItemDto);
        assertEquals(1, cartDto.getStockItemDtoList().size());
        cart = new Cart(1, "abdulmoeedak", new HashMap<String, Integer>(){{
            put("t-shirt",2);
            put("cup",1);
        }});
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        cartDto = mockedCartService.removeItemFromCart(stockItemDto);
        assertEquals(2, cartDto.getStockItemDtoList().size());
        assertTrue(cartDto.getStockItemDtoList().stream().anyMatch(stDto -> stDto.getItemName().equals("t-shirt") && stDto.getCartQuantity() == 1));
        cart = new Cart(1, "abdulmoeedak", new HashMap<String, Integer>(){{
            put("cup",1);
        }});
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        Exception exception = assertThrows(ItemNotInCartException.class, () -> mockedCartService.removeItemFromCart(stockItemDto));
        assertTrue(exception.getMessage().equals("Item not present in cart"));
    }
}
