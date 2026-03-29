package com.clothing.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.clothing.admin.entity.Category;
import com.clothing.admin.mapper.CategoryMapper;
import com.clothing.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.selectList(new LambdaQueryWrapper<Category>()
            .orderByAsc(Category::getSort));
    }

    @Override
    public List<Category> getCategoriesByParentId(Long parentId) {
        return categoryMapper.selectList(new LambdaQueryWrapper<Category>()
            .eq(Category::getParentId, parentId != null ? parentId : 0)
            .orderByAsc(Category::getSort));
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryMapper.selectById(id);
    }

    @Override
    @Transactional
    public Long createCategory(Category category) {
        categoryMapper.insert(category);
        return category.getId();
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        categoryMapper.updateById(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        categoryMapper.deleteById(id);
    }
}
