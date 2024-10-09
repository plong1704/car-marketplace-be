package com.market.carmarketservice.service.category;

import com.market.carmarketservice.model.category.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getCategories();

    public Category getCategory(int id);

    public boolean createCategory(Category category);

    public boolean updateCategory(Category category, int id);

    public boolean deleteCategory(int id);

    public boolean existCategory(int id);

    public boolean existCategoryName(String name);
}
