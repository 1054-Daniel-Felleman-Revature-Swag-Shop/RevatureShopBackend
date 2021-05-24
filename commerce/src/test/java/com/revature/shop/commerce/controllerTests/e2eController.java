package com.revature.shop.commerce.controllerTests;

import com.revature.shop.commerce.dto.*;
import com.revature.shop.commerce.model.*;

import org.junit.jupiter.api.Test;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class e2eController {

    RestTemplate restTemplate = new RestTemplate();

    //passes
    @Test
    public void welcomeToCommerceE2E()
    {
        URI uri = URI.create("http://localhost:8200/commerce/welcomeToCommerce");
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    //fails with a return 500 internal server error!
    @Test
    public void addtocartE2E(){
        //create resources to pass to controller method
        StockItemDto stockItemDto = new StockItemDto("hshallal", "t-shirt", 50, 10);

        //Create your http request
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<StockItemDto> entity = new HttpEntity<StockItemDto>(stockItemDto,headers);

        //Test the endpoint and catch your returned object in a postman fashion
        CartDto returnedCartDto = restTemplate.exchange("http://localhost:8200/commerce/addtocart", HttpMethod.PUT, entity, CartDto.class).getBody();

        //Some equality tests in the returned object
//        assertEquals(returnedCartDto.getMyShopper(), "hshallal");
//        assertEquals(returnedCartDto.getStockItemDtoList().size(), 1);
    }

    //fails with a return 500 internal server error!
    @Test
    public void removefromcartE2E(){
        // First create and persist a cart with the item to be removed
        Map<String, Integer> stockItemMap = new HashMap<String, Integer>();
        stockItemMap.put("t-shirt", 2);
        Cart toRemoveFromCart = new Cart(1, "hshallal", stockItemMap);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cart> entity = new HttpEntity<Cart>(toRemoveFromCart,headers);
        Cart returnedToRemoveFromCart = restTemplate.exchange("http://localhost:8200/commerce/savecart", HttpMethod.PUT, entity, Cart.class).getBody();

        //Now start with testing the removal
        //create resources to pass to controller method
        StockItemDto stockItemDto = new StockItemDto("hshallal", "t-shirt", 50, 10);

        //Create your http request
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<StockItemDto> toRemoveEntity = new HttpEntity<StockItemDto>(stockItemDto,headers);

        //Test the endpoint and catch your returned object in a postman fashion
        CartDto returnedCartDto = restTemplate.exchange("http://localhost:8200/commerce/removefromcart", HttpMethod.DELETE, toRemoveEntity, CartDto.class).getBody();

        //Some equality tests in the returned object
//        assertEquals(returnedCartDto.getMyShopper(), "hshallal");
//        assertEquals(returnedCartDto.getStockItemDtoList().size(), 1);
    }

    //passes
    @Test
    public void savecartE2E(){
        //create resources to pass to controller method
        Map<String, Integer> stockItemMap = new HashMap<String, Integer>();
        stockItemMap.put("t-shirt", 2);
        Cart toPersistCart = new Cart(1, "hshallal", stockItemMap);

        //Create your http request
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cart> entity = new HttpEntity<Cart>(toPersistCart,headers);

        //Test the endpoint and catch your returned object in a postman fashion
        Cart returnedCart = restTemplate.exchange("http://localhost:8200/commerce/savecart", HttpMethod.PUT, entity, Cart.class).getBody();

        //Some equality tests in the returned object
        assertEquals(returnedCart.getCartId(), 1);
        assertEquals(returnedCart.getMyShopper(), "hshallal");
        assertEquals(returnedCart.getStockItemMap().size(), 1);
        assertTrue(returnedCart.getStockItemMap().containsValue(2));
    }

    //Another method of testing that is shorter but it is not easy to test on the returned resource
    @Test
    public void savecartE2E2(){
        Map<String, Integer> stockItemMap = new HashMap<String, Integer>();
        stockItemMap.put("t-shirt", 2);
        Cart toPersistCart = new Cart(1, "hshallal", stockItemMap);

        URI uri = URI.create("http://localhost:8200/commerce/savecart");
        restTemplate.put(uri, toPersistCart);
    }

    //passes
    @Test
    public void checkoutcartE2E(){
        //create resources to pass to controller method
        Map<String, Integer> stockItemMap = new HashMap<String, Integer>();
        stockItemMap.put("t-shirt", 2);
        Cart toCheckoutCart = new Cart(1, "hshallal", stockItemMap);

        //Create your http request
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cart> entity = new HttpEntity<Cart>(toCheckoutCart,headers);

        //Test the endpoint and catch your returned object in a postman fashion
        Integer purchaseAmount = restTemplate.exchange("http://localhost:8200/commerce/checkoutcart", HttpMethod.POST, entity, Integer.class).getBody();

        //Some equality tests in the returned object
        // In this case, there is no t-shirt items persisted in the stockItemRepository and hence
        // the purchase points must be 0
        Integer expected = (Integer) 0;
        assertEquals(purchaseAmount, expected);
    }

    @Test
    public void mycartshopperE2E(){
        // First create and persist a cart with the item to be removed
        Map<String, Integer> stockItemMap = new HashMap<String, Integer>();
        stockItemMap.put("t-shirt", 2);
        Cart toPersistCart = new Cart(1, "hshallal", stockItemMap);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cart> entity = new HttpEntity<Cart>(toPersistCart,headers);
        restTemplate.exchange("http://localhost:8200/commerce/savecart", HttpMethod.PUT, entity, Cart.class).getBody();

        //Now we test whether we can get the persisted cart using shopper name
        //Create your http request
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        entity = new HttpEntity<>(headers);

        //Test the endpoint and catch your returned object in a postman fashion
        Cart myCart = restTemplate.exchange("http://localhost:8200/commerce/myCart/hshallal", HttpMethod.GET, entity, Cart.class).getBody();

        //Perform sanity checks on the returned cart
        assertEquals(myCart.getCartId(), 1);
        assertEquals(myCart.getMyShopper(), "hshallal");
        assertEquals(myCart.getStockItemMap().size(), 1);
        assertTrue(myCart.getStockItemMap().containsValue(2));
    }
}


