package com.clothing.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("inventory_log")
public class InventoryLog {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private Long skuId;
    
    private Integer changeQty;
    
    private Integer beforeQty;
    
    private Integer afterQty;
    
    private String type;
    
    private String reason;
    
    private Long relatedId;
    
    private Long operatorId;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
