package com.clothing.admin.service;

import com.clothing.admin.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    List<Category> getCategoriesByParentId(Long parentId);
    Category getCategoryById(Long id);
    Long createCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Long id);
}
