package com.clothing.admin.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductSkuDTO {
    private Long id;
    private Long productId;
    private String skuCode;
    private String color;
    private String size;
    private BigDecimal price;
    private Integer stock;
    private Integer status;
}
