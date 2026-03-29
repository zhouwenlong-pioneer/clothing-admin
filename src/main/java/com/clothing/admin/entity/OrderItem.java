package com.clothing.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("order_item")
public class OrderItem {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private Long orderId;
    
    private Long productId;
    
    private Long skuId;
    
    private String productName;
    
    private String skuName;
    
    private Integer quantity;
    
    private BigDecimal price;
    
    private BigDecimal totalAmount;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
