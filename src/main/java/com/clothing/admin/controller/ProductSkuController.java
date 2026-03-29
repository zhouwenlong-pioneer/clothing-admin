package com.clothing.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.clothing.admin.common.Result;
import com.clothing.admin.entity.ProductSku;
import com.clothing.admin.mapper.ProductSkuMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "商品 SKU 管理")
@RestController
@RequestMapping("/product/sku")
@RequiredArgsConstructor
public class ProductSkuController {

    private final ProductSkuMapper productSkuMapper;

    @Operation(summary = "获取商品 SKU 列表")
    @GetMapping("/list")
    public Result<List<ProductSku>> list(@RequestParam Long productId) {
        List<ProductSku> list = productSkuMapper.selectList(
            new LambdaQueryWrapper<ProductSku>().eq(ProductSku::getProductId, productId));
        return Result.success(list);
    }

    @Operation(summary = "创建商品 SKU")
    @PostMapping
    public Result<Long> create(@RequestBody ProductSku sku) {
        productSkuMapper.insert(sku);
        return Result.success(sku.getId());
    }

    @Operation(summary = "更新商品 SKU")
    @PutMapping
    public Result<Void> update(@RequestBody ProductSku sku) {
        productSkuMapper.updateById(sku);
        return Result.success();
    }

    @Operation(summary = "删除商品 SKU")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        productSkuMapper.deleteById(id);
        return Result.success();
    }
}
