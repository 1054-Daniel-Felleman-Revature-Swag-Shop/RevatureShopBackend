package com.revature.shop.repositories;

import com.revature.shop.models.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface InventoryRepository extends JpaRepository<StockItem, Integer>
{
    public void addInventory(int id);

    @Modifying
    @Query("update StockItem item set item.quantity = :quantity where item.itemId = :id")
    public void updateQuantity(@Param("quantity") Integer quantity, @Param("id") int id);
}
