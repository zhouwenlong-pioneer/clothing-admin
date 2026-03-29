package com.clothing.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.dto.ProductDTO;
import com.clothing.admin.entity.Product;
import java.util.List;

public interface ProductService {
    Page<Product> getProductPage(Integer pageNum, Integer pageSize, Long categoryId, String keyword);
    Product getProductById(Long id);
    Long createProduct(ProductDTO dto);
    void updateProduct(ProductDTO dto);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
}
