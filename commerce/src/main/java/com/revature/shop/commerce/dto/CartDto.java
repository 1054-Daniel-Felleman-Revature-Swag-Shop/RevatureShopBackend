package com.revature.shop.commerce.dto;

import java.util.List;

public class CartDto {

    private String cartId;
    private List<StockItemDto> stockItemDtoList;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }


    public List<StockItemDto> getStockItemDtoList() {
        return stockItemDtoList;
    }

    public void setStockItemDtoList(List<StockItemDto> stockItemDtoList) {
        this.stockItemDtoList = stockItemDtoList;
    }
}
