package com.revature.shop.commerce.controllerTests;

import com.revature.shop.commerce.dto.*;
import com.revature.shop.commerce.model.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class e2eController {

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

    //passes
    @Test
    public void welcomeToCommerceE2E()
    {
        URI uri = URI.create("http://localhost:9001/commercems/commerce/welcomeToCommerce");
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    //fails with a return 500 internal server error!
    @Test
    public void addtocartE2E(){
        try {
            restTemplate.put("http://localhost:9001/inventoryms/api/inventory/stockitem/new", stockItem);
        }
        catch(HttpClientErrorException e){
            e.printStackTrace();
            Assertions.fail();
        }

    }

    //fails with a return 500 internal server error!
    @Test
    public void removefromcartE2E(){
        try {
            restTemplate.delete("http://localhost:9001/inventoryms/api/inventory/delete/item/name?itemName="+stockItem.getItemName());
        }
        catch(HttpClientErrorException e){
            e.printStackTrace();
            Assertions.fail();
        }
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
        Cart returnedCart = restTemplate.exchange("http://localhost:9001/commercems/commerce/savecart", HttpMethod.POST, entity, Cart.class).getBody();

        //Some equality tests in the returned object
        assertEquals(returnedCart.getCartId(), 1);
        assertEquals(returnedCart.getMyShopper(), "hshallal");
        assertEquals(returnedCart.getStockItemMap().size(), 1);
        assertTrue(returnedCart.getStockItemMap().containsValue(2));
    }


    //passes
    @Test
    public void checkoutcartE2E(){
        //create resources to pass to controller method
        Map<String, Integer> stockItemMap = new HashMap<String, Integer>();
        stockItemMap.put("t-shirt", 2);
        stockItemMap.put("cup", 10);
        Cart toCheckoutCart = new Cart(1, "hshallal", stockItemMap);

        //Create your http request
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cart> entity = new HttpEntity<Cart>(toCheckoutCart,headers);

        //Test the endpoint and catch your returned object in a postman fashion
        Integer purchaseAmount = restTemplate.exchange("http://localhost:9001/commercems/commerce/checkoutcart", HttpMethod.POST, entity, Integer.class).getBody();

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
        Cart toPersistCart = new Cart(1, "hshallal@icloud.com", stockItemMap);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cart> entity = new HttpEntity<Cart>(toPersistCart,headers);
        restTemplate.exchange("http://localhost:9001/commercems/commerce/savecart", HttpMethod.POST, entity, Cart.class).getBody();

        //Now we test whether we can get the persisted cart using shopper name
        //Create your http request
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        entity = new HttpEntity<>(headers);

        //Test the endpoint and catch your returned object in a postman fashion
        Cart myCart = restTemplate.exchange("http://localhost:9001/commercems/commerce/myCart/hshallal@icloud.com", HttpMethod.GET, entity, Cart.class).getBody();

        //Perform sanity checks on the returned cart
        assertEquals(myCart.getCartId(), 1);
        assertEquals(myCart.getMyShopper(), "hshallal@icloud.com");
        assertEquals(myCart.getStockItemMap().size(), 1);
        assertTrue(myCart.getStockItemMap().containsValue(2));
    }

    @Test
    public void myshoppinghistoryE2E(){
        // First checkout a cart so that this checkout get followed by persisting a purchase history
        //create resources to pass to controller method
        Map<String, Integer> stockItemMap = new HashMap<String, Integer>();
        stockItemMap.put("t-shirt", 2);
        stockItemMap.put("cup", 10);
        Cart toCheckoutCart = new Cart(1, "hshallal", stockItemMap);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cart> entity = new HttpEntity<Cart>(toCheckoutCart,headers);
        Integer purchaseAmount = restTemplate.exchange("http://localhost:9001/commercems/commerce/checkoutcart", HttpMethod.POST, entity, Integer.class).getBody();


        //Test the endpoint and catch your returned object in a postman fashion
        List<PurchaseHistory> myPurchaseHistory = restTemplate.exchange("http://localhost:9001/commercems/commerce/myOrderHistory/hshallal@icloud.com", HttpMethod.GET, entity, List.class).getBody();

        //Perform sanity checks on the returned cart
//        assertEquals(myPurchaseHistory.size(), 2);

    }
}


