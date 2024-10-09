package com.market.carmarketservice.service.product;

import com.market.carmarketservice.model.product.ProductRepository;
import com.market.carmarketservice.model.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProduct(int id) {
        if (existProduct(id)) {
            Optional<Product> optional = productRepository.findById(id);
            Product product = optional.get();
            return product;
        }
        return null;
    }

    @Override
    public List<Product> getProductByBrand(int cid) {
        return (List<Product>) productRepository.getProductsByBrandId(cid);
    }

    @Override
    public boolean createProduct(Product other) {
        try {
            var product = Product.builder()
                    .name(other.getName())
                    .image(other.getImage())
                    .price(other.getPrice())
                    .type(other.getType())
                    .size(other.getSize())
                    .fuel(other.getFuel())
                    .power(other.getPower())
                    .color(other.getColor())
                    .description(other.getDescription())
                    .details(other.getDetails())
                    .category(other.getCategory())
                    .brand(other.getBrand())
                    .build();
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product, int id) {
        if (existProduct(id)) {
            product.setId(id);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        if (existProduct(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existProduct(int id) {
        return productRepository.existsProductsById(id);
    }

    @Override
    public List<Product> searchProducts(String text) {
        return (List<Product>) productRepository.searchProduct(text);
    }
}
