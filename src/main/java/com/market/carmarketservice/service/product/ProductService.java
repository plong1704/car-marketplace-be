package com.market.carmarketservice.service.product;

import com.market.carmarketservice.model.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(int id);

    List<Product> getProductByBrand(int cid);

    boolean createProduct(Product product);

    boolean updateProduct(Product product, int id);

    boolean deleteProduct(int id);

    boolean existProduct(int id);

    List<Product> searchProducts(String text);

}
