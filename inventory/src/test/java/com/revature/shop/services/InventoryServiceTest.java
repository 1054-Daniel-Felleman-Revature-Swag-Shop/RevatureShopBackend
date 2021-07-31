package com.revature.shop.services;

import com.revature.shop.MailService;
import com.revature.shop.models.StockItem;
import com.revature.shop.repositories.InventoryRepository;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.task.TaskExecutor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    @Mock
    InventoryRepository iRep = Mockito.mock(InventoryRepository.class);
    @Mock
    CircuitBreakerFactory cbf = Mockito.mock(CircuitBreakerFactory.class);
    @Mock
    MailService mailService = Mockito.mock(MailService.class);
    @Mock
    TaskExecutor taskExecutor = Mockito.mock(TaskExecutor.class);

    InventoryService iServ = new InventoryService(cbf, iRep, mailService, taskExecutor);


    StockItem s1 = new StockItem("Hat", 100, 1000, "Accessory", "A sweet hat", null, 0,false);
    StockItem s2 = new StockItem("Shirt", 1000, 100, "Clothing", "A cool shirt", null, 0,false);
    StockItem s3 = new StockItem("Socks", 10, 10000, "Clothing", "Some nice socks", null, 0,false);


    List<StockItem> sList = new ArrayList<StockItem>();

    @Before
    public void setUpTests(){
        sList.add(s1);
        sList.add(s2);
        sList.add(s3);

        Mockito.when(iRep.findAll()).thenReturn(sList);
        Mockito.when(iRep.findByQuantityEquals(0)).thenReturn(sList);
        Mockito.when(iRep.findByQuantityGreaterThan(0)).thenReturn(sList);
    }

}