package com.revature.shop.commerce.service;

import com.revature.shop.commerce.dtos.CartDto;
import com.revature.shop.commerce.dtos.PointChangeDto;
import com.revature.shop.commerce.dtos.StockItemDto;
import com.revature.shop.commerce.exceptions.*;
import com.revature.shop.commerce.models.Cart;
import com.revature.shop.commerce.models.PurchaseHistory;
import com.revature.shop.commerce.repositories.*;
import com.revature.shop.models.StockItem;

//import com.revature.shop.models.StockItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    PurchaseHistoryRepository purchaseHistoryRepository;

    RestTemplate restTemplate = new RestTemplate();

    String getStockItemQuery = "http://localhost:9001/inventoryms/api/inventory/get/item/id?id=";

    //Cart should be cleared after certain period of inactivity to release the items;
    @Transactional
    public CartDto updateCart(StockItemDto stockItemDto) throws ItemOutOfStockException {
        StockItem stockItem = restTemplate.getForObject(getStockItemQuery + stockItemDto.getId(), StockItem.class);
        if (stockItem != null && stockItem.getQuantity() > 0) {
            Cart cart = cartRepository.findOneByMyShopper(stockItemDto.getMyshopper());
            if (cart == null)
                cart = new Cart(stockItemDto.getMyshopper(), new HashMap<>());
            if (cart.getStockItemMap().containsKey(stockItemDto.getId()))
                cart.getStockItemMap().put(stockItemDto.getId(), cart.getStockItemMap().get(stockItemDto.getId()) + stockItemDto.getCartQuantity());
            else cart.getStockItemMap().put(stockItemDto.getId(), stockItemDto.getCartQuantity());
//            stockItem.setQuantity(stockItem.getQuantity() - 1);
//            stockItemRepository.save(stockItem);
            cartRepository.save(cart);
            List<StockItemDto> stockItemDtoList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry: cart.getStockItemMap().entrySet()) {
                stockItem = restTemplate.getForObject(getStockItemQuery + stockItemDto.getId(), StockItem.class);
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
        }
        else throw new ItemOutOfStockException();
    }

    @Transactional
    public CartDto removeItemFromCart (StockItemDto stockItemDto) throws ItemNotInCartException {
        Cart cart = cartRepository.findOneByMyShopper(stockItemDto.getMyshopper());
        List<StockItemDto> stockItemDtoList = new ArrayList<>();
        if (cart.getStockItemMap().containsKey(stockItemDto.getId())) {
            cart.getStockItemMap().put(stockItemDto.getId(), cart.getStockItemMap().get(stockItemDto.getId()) - 1);
            if (cart.getStockItemMap().get(stockItemDto.getId()) == 0)
                cart.getStockItemMap().remove(stockItemDto.getId());
            cartRepository.save(cart);
            for (Map.Entry<Integer, Integer> entry: cart.getStockItemMap().entrySet()) {
                StockItem stockItem = restTemplate.getForObject(getStockItemQuery + stockItemDto.getId(), StockItem.class);
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
        }
        else throw new ItemNotInCartException();
    }

    public Cart saveCart(Cart cart) throws UnableToSaveCartException {
        return cartRepository.save(cart);
    }

    @Transactional
    public double checkoutCart(Cart cart) throws UnableToCheckoutException
    {
        // compute your purchase total points
        double currentPurchaseTotal = 0;
        int numItems = 0;
        int totalItems = 0;
        String[] items = new String[cart.getStockItemMap().size()];
        for(Integer key : cart.getStockItemMap().keySet()){

            StockItem curStockItem = restTemplate.getForObject(getStockItemQuery + key, StockItem.class);
            if(curStockItem != null) {
                double thisItemTotal = curStockItem.getItemPrice() * (1 - curStockItem.getDiscount()) * cart.getStockItemMap().get(key);
                totalItems += cart.getStockItemMap().get(key);
                items[numItems] = curStockItem.getItemName() + " x "+cart.getStockItemMap().get(key)+", RevCoins:"+thisItemTotal;
                numItems++;
                //increment cart total
                currentPurchaseTotal += thisItemTotal;

                //persis a purchase history instance
                PurchaseHistory purchaseHistory = new PurchaseHistory(cart.getMyShopper(), LocalDate.now().toString(), curStockItem.getItemName(), cart.getStockItemMap().get(key), curStockItem.getItemPrice()* (1 - curStockItem.getDiscount()), thisItemTotal);
                purchaseHistoryRepository.save(purchaseHistory);
            }
        }
        try {
            restTemplate.postForObject("http://localhost:9001/accountsms/api/account/points/" + cart.getMyShopper(), new PointChangeDto("Purchased "+totalItems+" item(s) from the shop: " + Arrays.toString(items), -currentPurchaseTotal), Boolean.class);
        }
        catch(RestClientException e){

        }
        // set the map to an empty map, save cart
        Map<Integer, Integer> emptyStockItemMap = new HashMap<Integer, Integer>();
        cart.setStockItemMap(emptyStockItemMap);
        cartRepository.save(cart);

        // return total points
        return currentPurchaseTotal;
    }

    public Cart getShopperCart(String shopper){
        Cart cart = cartRepository.findOneByMyShopper(shopper);
        if(cart == null){
            cart = new Cart(shopper, new HashMap<>());
            cartRepository.save(cart);
        }
        return cart;
    }

    public List<PurchaseHistory> getShoppoingHistory(String shopperEmail){
        return purchaseHistoryRepository.findAllByMyShopper(shopperEmail);
    }
    
    public List<String> getMostPopular(){
    	
    	return purchaseHistoryRepository.findAndCountAllDistinct();
    	
    	
    }
}
