package com.revature.shop.services;

import com.revature.shop.models.StockItem;
import com.revature.shop.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InventoryService
{
    private final InventoryRepository iRep;

    @Autowired
    public InventoryService(InventoryRepository iRep) {
        this.iRep = iRep;
    }

    public List<StockItem> getAllStock(){
        return iRep.findAll();
    }

    public List<StockItem> getInStock(){
        return iRep.findByQuantityGreaterThan(0);
    }

    public List<StockItem> getOutOfStock(){
        return iRep.findByQuantityEquals(0);
    }

    public Boolean addToStock(StockItem sItem){
        if(iRep.existsById(sItem.getItemId())){
            return false;
        }
        else iRep.save((sItem));
        return true;
    }
    public Boolean updateStockItem(StockItem sItem){
        if (iRep.existsById(sItem.getItemId())){
            iRep.save(sItem);
            return true;
        }
        else return false;
    }

}
