package com.clothing.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.common.PageResult;
import com.clothing.admin.common.Result;
import com.clothing.admin.dto.ProductDTO;
import com.clothing.admin.entity.Product;
import com.clothing.admin.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "商品管理")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "获取商品列表")
    @GetMapping("/page")
    public Result<PageResult<List<Product>>> getPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String keyword) {
        Page<Product> page = productService.getProductPage(pageNum, pageSize, categoryId, keyword);
        PageResult<List<Product>> result = PageResult.of(
            page.getTotal(), page.getPages(), page.getCurrent(), page.getSize(), page.getRecords());
        return Result.success(result);
    }

    @Operation(summary = "获取商品详情")
    @GetMapping("/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        return Result.success(productService.getProductById(id));
    }

    @Operation(summary = "创建商品")
    @PostMapping
    public Result<Long> create(@Valid @RequestBody ProductDTO dto) {
        return Result.success(productService.createProduct(dto));
    }

    @Operation(summary = "更新商品")
    @PutMapping
    public Result<Void> update(@Valid @RequestBody ProductDTO dto) {
        productService.updateProduct(dto);
        return Result.success();
    }

    @Operation(summary = "删除商品")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return Result.success();
    }

    @Operation(summary = "获取所有商品")
    @GetMapping("/list")
    public Result<List<Product>> list() {
        return Result.success(productService.getAllProducts());
    }
}
