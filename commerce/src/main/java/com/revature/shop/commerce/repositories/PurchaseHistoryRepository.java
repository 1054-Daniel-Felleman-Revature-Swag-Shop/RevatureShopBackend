

package com.revature.shop.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.shop.commerce.models.PurchaseHistory;

import java.util.List;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, String> {
    List<PurchaseHistory> findAllByMyShopper(String myshopper);
}
