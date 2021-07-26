package com.revature.shop.services;

import com.revature.shop.models.StockItem;
import com.revature.shop.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository iRep;

    private final S3Client s3;

    public InventoryService(InventoryRepository iRep) {
        this(iRep, "", "");
    }

    @Autowired
    public InventoryService(InventoryRepository iRep, @Value("${aws.accessKeyId}") String id, @Value("${aws.secretAccessKey}") String key) {
        this.iRep = iRep;

        s3 = S3Client.builder().region(Region.US_EAST_2).credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(id, key))).build();
    }

    public List<StockItem> getAllStock() {
        return iRep.findAll();
    }

    public List<String> getAllCategories() {
        return iRep.getDistinctCategories();
    }

    public List<StockItem> getStockByCategory(String cat) {
        return iRep.findByCategory(cat);
    }

    public List<StockItem> getInStock() {
        return iRep.findByQuantityGreaterThan(0);
    }

    public List<StockItem> getOutOfStock() {
        return iRep.findByQuantityEquals(0);
    }

    public int addToStock(StockItem sItem, MultipartFile itemImage) {
        System.out.println("ADD TO STOCK start");

        if (iRep.existsById(sItem.getId())) {
            return -1;
        }

        iRep.save((sItem));
        System.out.println("New item with id: " + sItem.getId() + " created.");
        return sItem.getId();
    }

    public boolean updateStockItemQuantity(String name, int quantity) {
        if (iRep.findByItemName(name) != null) {
            iRep.updateQuantity(name, quantity);
            return true;
        }

        return false;
    }

    public StockItem getItemByName(String itemName) {
        return iRep.findByItemName(itemName);
    }

    public void deleteItemByName(String itemName) {
        iRep.deleteByItemName(itemName);
    }

    public boolean uploadImageForItemWithId(int id, MultipartFile file) {
        try {
            String itemId = String.valueOf(id);
            InputStream input = file.getInputStream();

            s3.putObject(PutObjectRequest.builder().bucket("revature-swag-shop-images").key(itemId).build(), RequestBody.fromInputStream(input, input.available()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
