package com.revature.shop.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.shop.commerce.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findOneByMyShopper(String myshopper);
}
