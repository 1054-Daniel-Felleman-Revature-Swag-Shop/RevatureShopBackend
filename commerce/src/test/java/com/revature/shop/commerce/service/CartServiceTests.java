package com.revature.shop.commerce.service;

import com.revature.shop.commerce.dto.CartDto;
import com.revature.shop.commerce.dto.StockItemDto;
import com.revature.shop.commerce.exception.ItemNotInCartException;
import com.revature.shop.commerce.exception.ItemOutOfStockException;

import com.revature.shop.commerce.model.Cart;
import com.revature.shop.commerce.repository.CartRepository;
import com.revature.shop.models.StockItem;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTests {

    @InjectMocks
    com.revature.shop.commerce.service.CartService mockedCartService;

    @Mock
    CartRepository cartRepository;

    static RestTemplate restTemplate = new RestTemplate();

    static StockItem stockItem = new StockItem("test-cup", 10, 10, null, null);
    static StockItem stockItem2 = new StockItem("test-t-shirt", 15, 20, null, null);

    @BeforeClass
    public static void addTestItem () {
        restTemplate.put("http://localhost:9001/inventoryms/api/inventory/stockitem/new", stockItem);
        restTemplate.put("http://localhost:9001/inventoryms/api/inventory/stockitem/new", stockItem2);
    }

    @AfterClass
    public static void deleteTestItem () {
        restTemplate.delete("http://localhost:9001/inventoryms/api/inventory/delete/item/name?itemName="+stockItem.getItemName());
        restTemplate.delete("http://localhost:9001/inventoryms/api/inventory/delete/item/name?itemName="+stockItem2.getItemName());
    }

    @Test
    public void updateCart() throws ItemOutOfStockException {
        Cart cart = new Cart(1, "abdulmoeedak", new HashMap<String, Integer>(){{
            put("test-t-shirt",1);
        }});
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        StockItemDto stockItemDto = new StockItemDto("abdulmoeedak", "test-cup", 10, 1, null, null);

        CartDto cartDto = mockedCartService.updateCart(stockItemDto);
        assertEquals(2, cartDto.getStockItemDtoList().size());
        cart = new Cart(1, "abdulmoeedak", new HashMap<String, Integer>(){{
            put("test-t-shirt",1);
            put("test-cup",1);
        }});
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        cartDto = mockedCartService.updateCart(stockItemDto);
        assertEquals(2, cartDto.getStockItemDtoList().size());
        assertTrue(cartDto.getStockItemDtoList().stream().anyMatch(stDto -> stDto.getItemName().equals("test-cup") && stDto.getCartQuantity() == 2));
        stockItem.setQuantity(0);
        restTemplate.put("http://localhost:9001/inventoryms/api/inventory/stockitem/update/quantity", stockItem);
        Exception exception = assertThrows(ItemOutOfStockException.class, () -> mockedCartService.updateCart(stockItemDto));
        assertTrue(exception.getMessage().equals("Item Out Of Stock"));
    }

    @Test
    public void removeItemFromCart () throws ItemNotInCartException {
        Cart cart = new Cart(1, "abdulmoeedak", new HashMap<String, Integer>(){{
            put("test-t-shirt",1);
            put("test-cup",1);
        }});
        StockItemDto stockItemDto = new StockItemDto("abdulmoeedak", "test-t-shirt", 10, 1, null, null);
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        CartDto cartDto = mockedCartService.removeItemFromCart(stockItemDto);
        assertEquals(1, cartDto.getStockItemDtoList().size());
        cart = new Cart(1, "abdulmoeedak", new HashMap<String, Integer>(){{
            put("test-t-shirt",2);
            put("test-cup",1);
        }});
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        cartDto = mockedCartService.removeItemFromCart(stockItemDto);
        assertEquals(2, cartDto.getStockItemDtoList().size());
        assertTrue(cartDto.getStockItemDtoList().stream().anyMatch(stDto -> stDto.getItemName().equals("test-t-shirt") && stDto.getCartQuantity() == 1));
        cart = new Cart(1, "abdulmoeedak", new HashMap<String, Integer>(){{
            put("test-cup",1);
        }});
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        Exception exception = assertThrows(ItemNotInCartException.class, () -> mockedCartService.removeItemFromCart(stockItemDto));
        assertTrue(exception.getMessage().equals("Item not present in cart"));
    }

}
