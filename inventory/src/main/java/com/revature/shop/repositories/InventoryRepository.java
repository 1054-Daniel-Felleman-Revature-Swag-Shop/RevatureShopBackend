package com.revature.shop.repositories;

import com.revature.shop.models.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface InventoryRepository extends JpaRepository<StockItem, Integer>
{
//    public void addInventory(int id);
    public StockItem findByItemName(String name);

    @Modifying
    @Query("update StockItem item set item.quantity = :quantity where item.itemName = :name")
    public void updateQuantity(@Param("name") String name, @Param("quantity") int quantity);

    @Modifying
    @Query("select distinct category from StockItem")
    public List<String> getDistinctCategories();

    public List<StockItem> findByCategory(String category);

//    public List<StockItem> findAllByOrderByItemIdAsc();
    public List<StockItem> findByQuantityGreaterThan(Integer quantity);
    public List<StockItem> findByQuantityEquals(Integer quantity);

    void deleteByItemName(String itemName);
}
