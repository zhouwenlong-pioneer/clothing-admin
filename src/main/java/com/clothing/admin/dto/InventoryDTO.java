package com.clothing.admin.dto;

import lombok.Data;

@Data
public class InventoryDTO {
    private Long id;
    private Long skuId;
    private Integer changeQty;
    private String type;
    private String reason;
    private Long relatedId;
}
