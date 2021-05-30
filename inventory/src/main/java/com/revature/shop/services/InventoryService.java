package com.revature.shop.services;

import com.revature.shop.models.StockItem;
import com.revature.shop.repositories.InventoryRepository;
import org.eclipse.jgit.transport.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

@Service
public class InventoryService
{
    private final InventoryRepository iRep;

    private final S3Client s3 = S3Client.builder().region(Region.US_EAST_2).build();
    /*private final AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withRegion(Regions.US_EAST_2)
            .build();*/

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

    public Integer addToStock(StockItem sItem, MultipartFile itemImage){
        System.out.println("ADD TO STOCK start");

        if(iRep.existsById(sItem.getId())){
            return -1;
        }
        else {
            iRep.save((sItem));
            System.out.println("New item with id: "+sItem.getId() + " created.");
            return sItem.getId();

        }

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

    public StockItem getItemByName(String itemName) {
        return iRep.findByItemName(itemName);
    }

    public void deleteItemByName(String itemName) {
        iRep.deleteByItemName(itemName);
    }

    public boolean uploadImageForItemWithId(Integer id, MultipartFile file) {

        InputStream input = null;
        try {

            String itemId = String.valueOf(id);

            input = file.getInputStream();
            try {
                s3.putObject(PutObjectRequest.builder().bucket("revature-swag-shop-images").key(itemId).build(), RequestBody.fromInputStream(input, input.available()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return true;


    }
}
