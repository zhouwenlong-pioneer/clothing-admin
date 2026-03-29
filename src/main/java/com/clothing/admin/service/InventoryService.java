package com.clothing.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.dto.InventoryDTO;
import com.clothing.admin.entity.Inventory;
import com.clothing.admin.entity.InventoryLog;

public interface InventoryService {
    Inventory getInventoryBySkuId(Long skuId);
    Page<InventoryLog> getInventoryLogPage(Integer pageNum, Integer pageSize, Long skuId);
    void stockIn(InventoryDTO dto);
    void stockOut(InventoryDTO dto);
    void stockCheck(InventoryDTO dto);
}
