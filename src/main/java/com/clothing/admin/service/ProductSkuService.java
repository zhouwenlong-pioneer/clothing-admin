package com.clothing.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.entity.ProductSku;
import java.util.List;

public interface ProductSkuService {
    List<ProductSku> getProductSkus(Long productId);
    ProductSku getProductSkuById(Long id);
    Long createProductSku(ProductSku sku);
    void updateProductSku(ProductSku sku);
    void deleteProductSku(Long id);
}
