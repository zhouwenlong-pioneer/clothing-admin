package com.clothing.admin.controller;

import com.clothing.admin.common.Result;
import com.clothing.admin.entity.Category;
import com.clothing.admin.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "分类管理")
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "获取所有分类")
    @GetMapping("/list")
    public Result<List<Category>> list() {
        return Result.success(categoryService.getAllCategories());
    }

    @Operation(summary = "获取子分类")
    @GetMapping("/children")
    public Result<List<Category>> getChildren(@RequestParam(required = false) Long parentId) {
        return Result.success(categoryService.getCategoriesByParentId(parentId));
    }

    @Operation(summary = "获取分类详情")
    @GetMapping("/{id}")
    public Result<Category> getById(@PathVariable Long id) {
        return Result.success(categoryService.getCategoryById(id));
    }

    @Operation(summary = "创建分类")
    @PostMapping
    public Result<Long> create(@RequestBody Category category) {
        return Result.success(categoryService.createCategory(category));
    }

    @Operation(summary = "更新分类")
    @PutMapping
    public Result<Void> update(@RequestBody Category category) {
        categoryService.updateCategory(category);
        return Result.success();
    }

    @Operation(summary = "删除分类")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }
}
