package com.revature.shop.commerce.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.revature.shop.models.StockItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.revature.shop.commerce.dtos.CartDto;
import com.revature.shop.commerce.dtos.PointChangeDto;
import com.revature.shop.commerce.dtos.StockItemDto;
import com.revature.shop.commerce.exceptions.ItemNotInCartException;
import com.revature.shop.commerce.exceptions.ItemOutOfStockException;
import com.revature.shop.commerce.exceptions.UnableToCheckoutException;
import com.revature.shop.commerce.exceptions.UnableToSaveCartException;
import com.revature.shop.commerce.models.Cart;
import com.revature.shop.commerce.models.PurchaseHistory;
import com.revature.shop.commerce.repositories.CartRepository;
import com.revature.shop.commerce.repositories.PurchaseHistoryRepository;
import com.revature.shop.models.StockItem;

@Service
public class CartService {
    CartRepository cartRepository;
    PurchaseHistoryRepository purchaseHistoryRepository;
    CircuitBreakerFactory cbf;
    
    RestTemplate restTemplate = new RestTemplate();

    String inventoryURI = "http://localhost:9001/inventoryms/api/inventory/get/item/id?id=";
    String accountURI = "http://localhost:9001/accountsms/api/account/points/";
    
    @Autowired
    public CartService(CartRepository cr, PurchaseHistoryRepository phr, CircuitBreakerFactory cbf) {
    	this.cartRepository = cr;
    	this.purchaseHistoryRepository = phr;
    	this.cbf = cbf;
    }

    //Cart should be cleared after certain period of inactivity to release the items;
    @Transactional
    public CartDto updateCart(StockItemDto stockItemDto) throws ItemOutOfStockException {
    	CircuitBreaker breaker = this.cbf.create("updateCart");
    	
    	StockItem stockItem = breaker.run(() -> this.restTemplate.getForObject(this.inventoryURI + stockItemDto.getId(), StockItem.class), throwable -> null);
        if (stockItem != null && stockItem.getQuantity() > 0) {
            Cart cart = this.cartRepository.findOneByMyShopper(stockItemDto.getMyshopper());
            if (cart == null) cart = new Cart(stockItemDto.getMyshopper(), new HashMap<>());
            
            if (cart.getStockItemMap().containsKey(stockItemDto.getId()))
                cart.getStockItemMap().put(stockItemDto.getId(), cart.getStockItemMap().get(stockItemDto.getId()) + stockItemDto.getCartQuantity());
            else cart.getStockItemMap().put(stockItemDto.getId(), stockItemDto.getCartQuantity());
            
            this.cartRepository.save(cart);
            List<StockItemDto> stockItemDtoList = new ArrayList<>();
            
            for (Map.Entry<Integer, Integer> entry: cart.getStockItemMap().entrySet()) {
            	stockItem = breaker.run(() -> this.restTemplate.getForObject(this.inventoryURI + stockItemDto.getId(), StockItem.class), throwable -> new StockItem());
                StockItemDto stockItemDto1 = new StockItemDto();
                stockItemDto1.setId(entry.getKey());
                stockItemDto1.setItemName(stockItem.getItemName());
                stockItemDto1.setCartQuantity(entry.getValue());
                stockItemDto1.setItemPrice(stockItem.getItemPrice());
                stockItemDto1.setCategory(stockItem.getCategory());
                stockItemDto1.setDescription(stockItem.getDescription());
                stockItemDtoList.add(stockItemDto1);
            }
            
            return new CartDto(cart.getMyShopper(), stockItemDtoList);
        } else throw new ItemOutOfStockException();
    }

    @Transactional
    public CartDto removeItemFromCart (StockItemDto stockItemDto) throws ItemNotInCartException {
    	CircuitBreaker breaker = this.cbf.create("removeItemFromCart");
    	
        Cart cart = this.cartRepository.findOneByMyShopper(stockItemDto.getMyshopper());
        List<StockItemDto> stockItemDtoList = new ArrayList<>();
        if (cart.getStockItemMap().containsKey(stockItemDto.getId())) {
            cart.getStockItemMap().put(stockItemDto.getId(), cart.getStockItemMap().get(stockItemDto.getId()) - 1);
            
            if (cart.getStockItemMap().get(stockItemDto.getId()) == 0) cart.getStockItemMap().remove(stockItemDto.getId());
            
            this.cartRepository.save(cart);
            
            for (Map.Entry<Integer, Integer> entry: cart.getStockItemMap().entrySet()) {
            	StockItem stockItem = breaker.run(() -> this.restTemplate.getForObject(this.inventoryURI + stockItemDto.getId(), StockItem.class), throwable -> new StockItem());
                StockItemDto stockItemDto1 = new StockItemDto();
                stockItemDto1.setId(entry.getKey());
                stockItemDto1.setItemName(stockItem.getItemName());
                stockItemDto1.setCartQuantity(entry.getValue());
                stockItemDto1.setItemPrice(stockItem.getItemPrice());
                stockItemDto1.setCategory(stockItem.getCategory());
                stockItemDto1.setDescription(stockItem.getDescription());
                stockItemDto1.setSize(stockItem.getSize());
                stockItemDtoList.add(stockItemDto1);
            }
            
            return new CartDto(cart.getMyShopper(), stockItemDtoList);
        } else throw new ItemNotInCartException();
    }

    public Cart saveCart(Cart cart) throws UnableToSaveCartException {
        return this.cartRepository.save(cart);
    }

    @Transactional
    public double checkoutCart(Cart cart) throws UnableToCheckoutException {
    	CircuitBreaker breaker = this.cbf.create("checkoutCart");
    	
        // compute your purchase total points
        double currentPurchaseTotal = 0;
        int numItems = 0;
        int totalItems = 0;
        
        String[] items = new String[cart.getStockItemMap().size()];
        for(Integer key : cart.getStockItemMap().keySet()) {
        	StockItem curStockItem = breaker.run(() -> this.restTemplate.getForObject(this.inventoryURI + key, StockItem.class), throwable -> null);
            if(curStockItem != null) {
                double thisItemTotal = curStockItem.getItemPrice() * (1 - curStockItem.getDiscount()) * cart.getStockItemMap().get(key);
                totalItems += cart.getStockItemMap().get(key);
                items[numItems] = curStockItem.getItemName() + " x " + cart.getStockItemMap().get(key) + ", RevCoins: " + thisItemTotal;
                numItems++;
                //increment cart total
                currentPurchaseTotal += thisItemTotal;

                //persist a purchase history instance
                PurchaseHistory purchaseHistory = new PurchaseHistory(cart.getMyShopper(), LocalDate.now().toString(), curStockItem.getItemName(), cart.getStockItemMap().get(key), curStockItem.getItemPrice() * (1 - curStockItem.getDiscount()), thisItemTotal);
                this.purchaseHistoryRepository.save(purchaseHistory);
            }
        }
        
        final double purchaseTotal = currentPurchaseTotal;
        String pcMessage = String.format("Purchased %d item%s from the shop: %s", totalItems, totalItems > 1 ? "s" : "", Arrays.toString(items));
        
        return breaker.run(() -> {
        	boolean success = this.restTemplate.postForObject(this.accountURI + cart.getMyShopper(), new PointChangeDto(pcMessage, -purchaseTotal), Boolean.class);
        	if (success) {
        		// set the map to an empty map, save cart
        		Map<Integer, Integer> emptyStockItemMap = new HashMap<Integer, Integer>();
                cart.setStockItemMap(emptyStockItemMap);
                this.cartRepository.save(cart);
                
                // return total points
                return purchaseTotal;
        	} else return -1d;
        }, (throwable) -> -1d);
    }

    public Cart getShopperCart(String shopper) {
        Cart cart = this.cartRepository.findOneByMyShopper(shopper);
        if(cart == null){
            cart = new Cart(shopper, new HashMap<>());
            this.cartRepository.save(cart);
        }
        return cart;
    }

    public List<PurchaseHistory> getShoppingHistory(String shopperEmail) {
        return this.purchaseHistoryRepository.findAllByMyShopper(shopperEmail);
    }
}
