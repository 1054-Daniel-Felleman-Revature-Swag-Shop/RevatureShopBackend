package com.revature.shop.services;

import com.revature.shop.models.StockItem;
import com.revature.shop.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public List<StockItem> getStockByCategory(String cat){
        return iRep.findByCategory(cat);
    }

    public List<StockItem> getInStock(){
        return iRep.findByQuantityGreaterThan(0);
    }

    public List<StockItem> getOutOfStock(){
        return iRep.findByQuantityEquals(0);
    }

    public Boolean addToStock(StockItem sItem){
        if(iRep.existsById(sItem.getId())){
            return false;
        }
        else iRep.save((sItem));
        return true;
    }

    public Boolean updateStockItemQuantity(String name, int quantity)
    {
        if (iRep.findByItemName(name) != null)
        {
            iRep.updateQuantity(name, quantity);
            return true;
        }
        else return false;
    }
}
