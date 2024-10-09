package com.market.carmarketservice.service.brand;

import com.market.carmarketservice.model.brand.Brand;

import java.util.List;

public interface BrandService {
    public List<Brand> getBrands();

    public Brand getBrand(int id);

    public boolean createBrand(Brand brand);

    public boolean updateBrand(Brand brand, int id);

    public boolean deleteBrand(int id);

    public boolean existBrand(int id);

    public boolean existBrandName(String name);
}
