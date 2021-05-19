package com.revature.shop.commerce.repository;

import com.revature.shop.commerce.model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemRepository extends JpaRepository<StockItem, Integer> {
    StockItem findByName(String key);
}
