package com.revature.shop.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointChange, Integer> {
}
