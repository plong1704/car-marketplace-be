package com.market.carmarketservice.model.brand;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsBrandById(int id);

    boolean existsBrandByName(String name);
}
