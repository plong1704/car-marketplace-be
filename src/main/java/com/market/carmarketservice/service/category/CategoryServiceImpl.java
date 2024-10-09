package com.market.carmarketservice.service.category;

import com.market.carmarketservice.model.category.Category;
import com.market.carmarketservice.model.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category getCategory(int id) {
        if (existCategory(id)) {
            Optional<Category> optional = categoryRepository.findById(id);
            Category category = optional.get();
            return category;
        }
        return null;
    }

    @Override
    public boolean createCategory(Category other) {
        if (existCategoryName(other.getName()))
            return false;
        try {
            var category = Category.builder()
                    .name(other.getName())
                    .build();
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateCategory(Category category, int id) {
        if (existCategory(id)) {
            category.setId(id);
            categoryRepository.save(category);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCategory(int id) {
        if (existCategory(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existCategory(int id) {
        return categoryRepository.existsCategoryById(id);
    }

    @Override
    public boolean existCategoryName(String name) {
        return categoryRepository.existsCategoryByName(name);
    }
}
