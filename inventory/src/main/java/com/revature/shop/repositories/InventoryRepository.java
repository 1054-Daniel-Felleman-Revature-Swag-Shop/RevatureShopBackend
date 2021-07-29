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
    public StockItem findByItemName(String name);

    @Modifying
    @Query("update StockItem item set item.quantity = :quantity where item.id = :id")
    public void updateQuantity(@Param("id") int id, @Param("quantity") int quantity);

    @Modifying
    @Query("select distinct category from StockItem")
    public List<String> getDistinctCategories();

    public List<StockItem> findByCategory(String category);
    public List<StockItem> findByIsFeatured(boolean bool);

    public List<StockItem> findByQuantityGreaterThan(Integer quantity);
    public List<StockItem> findByQuantityEquals(Integer quantity);

    void deleteByItemName(String itemName);

	public StockItem findByItemNameAndSize(String itemName, String size);
    
    @Modifying
    @Query("update StockItem item set item.discount = :discount where item.id = :id")
	public void updateDiscount(@Param("id") int id, @Param("discount") Double discount);
}
