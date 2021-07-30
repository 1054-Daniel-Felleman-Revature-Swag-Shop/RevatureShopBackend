

package com.revature.shop.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.shop.commerce.models.PurchaseHistory;

import java.util.List;
import java.util.Map;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, String> {
    List<PurchaseHistory> findAllByMyShopper(String myshopper);
    
    @Modifying
    @Query(value = "Select item_id, item_name, Sum(item_quantity) from purchase_history Group By item_Id order by Sum(item_quantity) desc limit 5", nativeQuery=true)
    public List<String> findAndCountAllDistinct();
}
