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

    static StockItemDto cartItem = new StockItemDto("hshallal@icloud.com", "Rev It Up Hat", 10, 1, "Accessories", "A sweet hat to ACCELERATE your development!" );
    static StockItemDto cartItem2 = new StockItemDto("hshallal@icloud.com", "Code Like A Boss T-Shirt", 100, 1, "Clothing", "Perfect for casual friday!" );

    //passes
    @Test
    public void a_welcomeToCommerceE2E()
    {
        URI uri = URI.create("http://localhost:9001/commercems/commerce/welcomeToCommerce");
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    //passes
    @Test
    public void b_savecartE2E(){
        //create resources to pass to controller method
        Map<String, Integer> stockItemMap = new HashMap<String, Integer>();
        Cart toPersistCart = new Cart(1, "hshallal@icloud.com", stockItemMap);

        //Create your http request
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cart> entity = new HttpEntity<Cart>(toPersistCart,headers);

        //Test the endpoint and catch your returned object in a postman fashion
        Cart returnedCart = restTemplate.exchange("http://localhost:9001/commercems/commerce/savecart", HttpMethod.POST, entity, Cart.class).getBody();

        //Some equality tests in the returned object
//        assertEquals(returnedCart.getCartId(), 1);
        assertEquals(returnedCart.getMyShopper(), "hshallal@icloud.com");
        assertEquals(returnedCart.getStockItemMap().size(), 0);

    }

    //fails with a return 500 internal server error!
    @Test
    public void c_addtocartE2E(){
        try {
            restTemplate.put("http://localhost:9001/commercems/commerce/addtocart", cartItem);
            restTemplate.put("http://localhost:9001/commercems/commerce/addtocart", cartItem2);
        }
        catch(HttpClientErrorException e){
            e.printStackTrace();
            Assertions.fail();
        }
    }

    //fails with a return 500 internal server error!
    @Test
    public void d_removefromcartE2E(){
        try {
            URI v = restTemplate.postForLocation("http://localhost:9001/commercems/commerce/removefromcart", cartItem2);
        }
        catch(HttpClientErrorException e){
            e.printStackTrace();
            Assertions.fail();
        }
    }

    //passes
    @Test
    public void f_checkoutcartE2E(){
        //create resources to pass to controller method
        //Create your http request
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String shopperEmail = "hshallal@icloud.com";
        HttpEntity<String> entity0 = new HttpEntity<String>(shopperEmail, headers);
        Cart toCheckoutCart = restTemplate.exchange("http://localhost:9001/commercems/commerce/myCart/hshallal@icloud.com", HttpMethod.GET, entity0, Cart.class).getBody();

        //Create your http request
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cart> entity = new HttpEntity<Cart>(toCheckoutCart,headers);

        //Test the endpoint and catch your returned object in a postman fashion
        Integer purchaseAmount = restTemplate.exchange("http://localhost:9001/commercems/commerce/checkoutcart", HttpMethod.POST, entity, Integer.class).getBody();

        //Some equality tests in the returned object
        Integer expected = (Integer) 120;
        assertEquals(purchaseAmount, expected);
    }

    @Test
    public void e_mycartshopperE2E(){

        //Now we test whether we can get the persisted cart using shopper name
        //Create your http request
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String shopperEmail = "hshallal@icloud.com";
        HttpEntity<String> entity0 = new HttpEntity<String>(shopperEmail, headers);
        //Test the endpoint and catch your returned object in a postman fashion
        Cart myCart = restTemplate.exchange("http://localhost:9001/commercems/commerce/myCart/hshallal@icloud.com", HttpMethod.GET, entity0, Cart.class).getBody();

        //Perform sanity checks on the returned cart
        assertEquals(myCart.getMyShopper(), "hshallal@icloud.com");
        assertEquals(myCart.getStockItemMap().size(), 0);
    }

    @Test
    public void g_myshoppinghistoryE2E(){
        //
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String shopperEmail = "hshallal@icloud.com";
        HttpEntity<String> entity0 = new HttpEntity<String>(shopperEmail, headers);


        //Test the endpoint and catch your returned object in a postman fashion
        List<PurchaseHistory> myPurchaseHistory = restTemplate.exchange("http://localhost:9001/commercems/commerce/myOrderHistory/hshallal@icloud.com", HttpMethod.GET, entity0, List.class).getBody();
        System.out.println(myPurchaseHistory);
        //Perform sanity checks on the returned cart
        assertEquals(myPurchaseHistory.size(), 2);
    }
}


