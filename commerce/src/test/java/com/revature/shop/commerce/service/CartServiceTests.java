package com.revature.shop.commerce.service;

import com.revature.shop.commerce.dtos.CartDto;
import com.revature.shop.commerce.dtos.StockItemDto;
import com.revature.shop.commerce.exceptions.ItemNotInCartException;
import com.revature.shop.commerce.exceptions.ItemOutOfStockException;
import com.revature.shop.commerce.models.Cart;
import com.revature.shop.commerce.repositories.CartRepository;
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
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTests {

    @InjectMocks
    com.revature.shop.commerce.service.CartService mockedCartService;

    @Mock
    CartRepository cartRepository;
    
    private final static String testCup = "test-cup";
    private final static String testShirt = "test-t-shirt";
    private final static String testName = "abdulmoeedak";

    static RestTemplate restTemplate = new RestTemplate();

    static StockItem stockItem = new StockItem("test-cup", 10, 10, null, null, null, 0);
    static StockItem stockItem2 = new StockItem("test-t-shirt", 15, 20, null, null, null, 0);

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
        Cart cart = new Cart(1, "abdulmoeedak", new HashMap<Integer, Integer>(){{
            put(1,1);
        }});
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        StockItemDto stockItemDto = new StockItemDto("abdulmoeedak", 2, "test-cup", 10, 1, null, null, null);

        CartDto cartDto = mockedCartService.updateCart(stockItemDto);
        assertEquals(2, cartDto.getStockItemDtoList().size());
        cart = new Cart(1, "abdulmoeedak", new HashMap<Integer, Integer>(){{
            put(1,1);
            put(2,1);
        }});
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        cartDto = mockedCartService.updateCart(stockItemDto);
        assertEquals(2, cartDto.getStockItemDtoList().size());
        assertTrue(cartDto.getStockItemDtoList().stream().anyMatch(stDto -> stDto.getItemName().equals(testCup) && stDto.getCartQuantity() == 2));
        stockItem.setQuantity(0);
        restTemplate.put("http://localhost:9001/inventoryms/api/inventory/stockitem/update/quantity", stockItem);
        Exception exception = assertThrows(ItemOutOfStockException.class, () -> mockedCartService.updateCart(stockItemDto));
        assertTrue(exception.getMessage().equals("Item Out Of Stock"));
    }

    @Test
    public void removeItemFromCart () throws ItemNotInCartException {
        Cart cart = new Cart(1, "abdulmoeedak", new HashMap<Integer, Integer>(){{
            put(1,1);
            put(2,1);
        }});
        StockItemDto stockItemDto = new StockItemDto("abdulmoeedak", 1, "test-t-shirt", 10, 1, null, null, null);
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        CartDto cartDto = mockedCartService.removeItemFromCart(stockItemDto);
        assertEquals(1, cartDto.getStockItemDtoList().size());
        cart = new Cart(1, "abdulmoeedak", new HashMap<Integer, Integer>(){{
            put(1,2);
            put(2,1);
        }});
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        cartDto = mockedCartService.removeItemFromCart(stockItemDto);
        assertEquals(2, cartDto.getStockItemDtoList().size());
        assertTrue(cartDto.getStockItemDtoList().stream().anyMatch(stDto -> stDto.getItemName().equals("test-t-shirt") && stDto.getCartQuantity() == 1));
        cart = new Cart(1, "abdulmoeedak", new HashMap<Integer, Integer>(){{
            put(2,1);
        }});
        when(cartRepository.findOneByMyShopper("abdulmoeedak")).thenReturn(cart);
        Exception exception = assertThrows(ItemNotInCartException.class, () -> mockedCartService.removeItemFromCart(stockItemDto));
        assertTrue(exception.getMessage().equals("Item not present in cart"));
    }

}
