package com.revature.shop.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

//import com.revature.shop.commerce.dtos.PointChangeDto;
import com.revature.shop.models.StockItem;
import com.revature.shop.repositories.InventoryRepository;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


@Service
@PropertySource("classpath:aws.properties")
public class InventoryService {
    private final InventoryRepository iRep;
    private final Logger logger = LogManager.getLogger();
    private final S3Client s3;
    
    CircuitBreakerFactory cbf;
    RestTemplate restTemplate = new RestTemplate();
    String commerceURI = "http://localhost:9001/commercems/commerce/allOrderHistory/mostPopular";

    public InventoryService(CircuitBreakerFactory cbf, InventoryRepository iRep) {
        this(cbf, iRep, "", "");
    }

    @Autowired
    public InventoryService(CircuitBreakerFactory cbf, InventoryRepository iRep, @Value("${aws.accessKeyId}") String id, @Value("${aws.secretAccessKey}") String key) {
    	this.cbf = cbf;
        this.iRep = iRep;

        s3 = S3Client.builder().region(Region.US_EAST_2).credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(id, key))).build();
    }

    public List<StockItem> getAllStock() {
        return iRep.findAll();
    }
    
    public List<StockItem> getDiscounted() {
		List<StockItem> allList = iRep.findAll();
		List<StockItem> disList = new ArrayList<StockItem>();
		for (StockItem s : allList) {
			if(s.getDiscount() != 0) {
				disList.add(s);
			}
		}
		return disList;
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
    public List<StockItem> getIsFeatured(boolean bool){
    	return iRep.findByIsFeatured(bool);
    }
    public List<StockItem> getMostPopular() {
    	CircuitBreaker breaker = this.cbf.create("getMostPopular");
    	ResponseEntity<String[]> mostPopString = breaker.run(() -> this.restTemplate.getForEntity(this.commerceURI, String[].class), (throwable) -> new ResponseEntity<String[]>(new String[0], HttpStatus.BAD_REQUEST));
    	String[] arr = mostPopString.getBody();
    	List<StockItem> finList = new ArrayList<StockItem>();
    	for(String s: arr) {
    		String[] item = s.split(",", 3);
    		finList.add(iRep.getById(Integer.parseInt(item[0])));
    	}
    	return finList;
    }

    public int addToStock(StockItem sItem) {
        System.out.println("ADD TO STOCK start");

        if (iRep.existsById(sItem.getId())) {
            return -1;
        }

        iRep.save((sItem));
        return sItem.getId();
    }

    public boolean updateStockItemQuantity(int id, int quantity) {
        if (iRep.findById(id) != null) {
            iRep.updateQuantity(id, quantity);
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

            //TODO: verify that the string passed into bucket() points to the right place
            s3.putObject(PutObjectRequest.builder().bucket("rss-images/images/").key(itemId).build(), RequestBody.fromInputStream(input, input.available()));
        } catch (IOException e) {
            logger.error(e.toString());
            return false;
        }
        return true;
    }

	public StockItem getItemByNameAndSize(String itemName, String size) {
		return iRep.findByItemNameAndSize(itemName, size);
	}

	public StockItem getItemById(int id) {
		return iRep.findById(id).get();
	}
    
    public boolean updateItemDiscount(int id, Double discount) {
    	if (iRep.findById(id) != null) {
            iRep.updateDiscount(id, discount);
            return true;
        }

        return false;
    }
}
