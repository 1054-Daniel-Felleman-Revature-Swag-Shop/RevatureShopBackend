package com.revature.shop.commerce.repository;

import com.revature.shop.commerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findOneByMyShopper(String myshopper);
}
