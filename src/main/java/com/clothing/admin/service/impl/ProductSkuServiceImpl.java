package com.clothing.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.clothing.admin.entity.ProductSku;
import com.clothing.admin.mapper.ProductSkuMapper;
import com.clothing.admin.service.ProductSkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSkuServiceImpl implements ProductSkuService {

    private final ProductSkuMapper productSkuMapper;

    @Override
    public List<ProductSku> getProductSkus(Long productId) {
        return productSkuMapper.selectList(new LambdaQueryWrapper<ProductSku>()
            .eq(ProductSku::getProductId, productId));
    }

    @Override
    public ProductSku getProductSkuById(Long id) {
        return productSkuMapper.selectById(id);
    }

    @Override
    @Transactional
    public Long createProductSku(ProductSku sku) {
        productSkuMapper.insert(sku);
        return sku.getId();
    }

    @Override
    @Transactional
    public void updateProductSku(ProductSku sku) {
        productSkuMapper.updateById(sku);
    }

    @Override
    @Transactional
    public void deleteProductSku(Long id) {
        productSkuMapper.deleteById(id);
    }
}
