package com.market.carmarketservice.api;

import com.market.carmarketservice.model.product.Product;
import com.market.carmarketservice.request.search.ProductRequest;
import com.market.carmarketservice.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@PropertySource("classpath:notify.properties")
@RequestMapping(value = "/api")
public class ProductController {
    private final ProductService productService;
    private final Environment env;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<Object> getProducts() {
        try {
            return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(env.getProperty("NotFound"), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProduct(@PathVariable("id") int id) {
        if (productService.getProduct(id) != null)
            return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("NotFound"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/brand/{id}/product", method = RequestMethod.GET)
    public ResponseEntity<Object> getProductByBrand(@PathVariable("id") int cid) {
        if (productService.getProductByBrand(cid) != null)
            return new ResponseEntity<>(productService.getProductByBrand(cid), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("NotFound"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/auth/product", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        if (productService.createProduct(product))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/auth/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        if (productService.updateProduct(product, id))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/auth/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") int id) {
        if (productService.deleteProduct(id))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<Object> findAll(@RequestBody ProductRequest request) {
        if (productService.searchProducts(request.getText()) != null)
            return new ResponseEntity<>(productService.searchProducts(request.getText()), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }
}
