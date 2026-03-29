package com.clothing.admin.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    
    @NotBlank(message = "商品名称不能为空")
    private String name;
    
    private String description;
    
    private Long categoryId;
    
    private String mainImage;
    
    private String images;
    
    private BigDecimal price;
    
    private Integer status;
}
