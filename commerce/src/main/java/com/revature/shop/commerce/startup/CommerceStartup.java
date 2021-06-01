package com.revature.shop.commerce.startup;

import com.revature.shop.commerce.exception.UnableToCheckoutException;
import com.revature.shop.commerce.model.Cart;
import com.revature.shop.commerce.model.PurchaseHistory;
import com.revature.shop.commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommerceStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    CartService cartService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        dumpDummy("parkerkt77@gmail.com", new HashMap<>(){{
            put("Rev It Up Hat", 4);
            put("Code Like A Boss T-Shirt", 5);
        }});
        dumpDummy("kent.berry@revature.net", new HashMap<>(){{
            put("SQL T-Shirt", 2);
            put("Stale Joke T-Shirt", 5);
            put("2021 T-Shirt", 10);
        }});
    }

    public void dumpDummy (String myShopper, Map<String, Integer> cartItems) {
        List<PurchaseHistory> purchaseHistoryList = cartService.getShoppoingHistory(myShopper);
        if (purchaseHistoryList.isEmpty()) {
            try {
                cartService.checkoutCart(new Cart(myShopper, cartItems));
            } catch (UnableToCheckoutException e) {
                e.printStackTrace();
            }
        }
    }

}
