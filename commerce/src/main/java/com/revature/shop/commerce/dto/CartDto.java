package com.revature.shop.commerce.dto;

import java.util.List;

public class CartDto {

    private String myShopper;
    private List<StockItemDto> stockItemDtoList;

    public CartDto() {
    }

    public CartDto(String myShopper, List<StockItemDto> stockItemDtoList) {
        this.myShopper = myShopper;
        this.stockItemDtoList = stockItemDtoList;
    }

    public String getMyShopper() {
        return myShopper;
    }

    public void setMyShopper(String myShopper) {
        this.myShopper = myShopper;
    }

    public List<StockItemDto> getStockItemDtoList() {
        return stockItemDtoList;
    }

    public void setStockItemDtoList(List<StockItemDto> stockItemDtoList) {
        this.stockItemDtoList = stockItemDtoList;
    }
}