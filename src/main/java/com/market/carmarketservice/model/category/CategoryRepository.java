package com.market.carmarketservice.model.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsCategoryById(int id);
    boolean existsCategoryByName(String name);
}
