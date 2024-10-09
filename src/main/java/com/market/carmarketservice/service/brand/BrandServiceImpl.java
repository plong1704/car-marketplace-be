package com.market.carmarketservice.service.brand;

import com.market.carmarketservice.model.brand.Brand;
import com.market.carmarketservice.model.brand.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public List<Brand> getBrands() {
        return (List<Brand>) brandRepository.findAll();
    }

    @Override
    public Brand getBrand(int id) {
        if (existBrand(id)) {
            Optional<Brand> optional = brandRepository.findById(id);
            Brand brand = optional.get();
            return brand;
        }
        return null;
    }

    @Override
    public boolean createBrand(Brand other) {
        if (existBrandName(other.getName()))
            return false;
        try {
            var brand = Brand.builder()
                    .name(other.getName())
                    .logo(other.getLogo())
                    .build();
            brandRepository.save(brand);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateBrand(Brand brand, int id) {
        if (existBrand(id)) {
            brand.setId(id);
            brandRepository.save(brand);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBrand(int id) {
        if (existBrand(id)) {
            brandRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existBrand(int id) {
        return brandRepository.existsBrandById(id);
    }

    @Override
    public boolean existBrandName(String name) {
        return brandRepository.existsBrandByName(name);
    }
}
