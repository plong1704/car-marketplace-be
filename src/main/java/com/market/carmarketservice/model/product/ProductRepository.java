package com.market.carmarketservice.model.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsProductsById(int id);

    List<Product> getProductsByBrandId(int cid);

    @Query("SELECT p from Product p where p.name like %:text%")
    List<Product> searchProduct(@Param("text") String text);
}
