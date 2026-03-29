package com.clothing.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.dto.InventoryDTO;
import com.clothing.admin.entity.Inventory;
import com.clothing.admin.entity.InventoryLog;
import com.clothing.admin.exception.BusinessException;
import com.clothing.admin.mapper.InventoryMapper;
import com.clothing.admin.mapper.InventoryLogMapper;
import com.clothing.admin.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryMapper inventoryMapper;
    private final InventoryLogMapper inventoryLogMapper;

    @Override
    public Inventory getInventoryBySkuId(Long skuId) {
        return inventoryMapper.selectOne(new LambdaQueryWrapper<Inventory>()
            .eq(Inventory::getSkuId, skuId));
    }

    @Override
    public Page<InventoryLog> getInventoryLogPage(Integer pageNum, Integer pageSize, Long skuId) {
        Page<InventoryLog> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<InventoryLog> wrapper = new LambdaQueryWrapper<>();
        
        if (skuId != null) {
            wrapper.eq(InventoryLog::getSkuId, skuId);
        }
        
        wrapper.orderByDesc(InventoryLog::getCreateTime);
        return inventoryLogMapper.selectPage(page, wrapper);
    }

    @Override
    @Transactional
    public void stockIn(InventoryDTO dto) {
        Inventory inventory = inventoryMapper.selectOne(new LambdaQueryWrapper<Inventory>()
            .eq(Inventory::getSkuId, dto.getSkuId()));
        
        if (inventory == null) {
            inventory = new Inventory();
            inventory.setSkuId(dto.getSkuId());
            inventory.setStock(0);
            inventory.setLockedStock(0);
            inventoryMapper.insert(inventory);
        }
        
        int beforeQty = inventory.getStock();
        int afterQty = beforeQty + dto.getChangeQty();
        inventory.setStock(afterQty);
        inventory.setAvailableStock(afterQty - inventory.getLockedStock());
        inventoryMapper.updateById(inventory);
        
        createInventoryLog(dto, beforeQty, afterQty);
    }

    @Override
    @Transactional
    public void stockOut(InventoryDTO dto) {
        Inventory inventory = inventoryMapper.selectOne(new LambdaQueryWrapper<Inventory>()
            .eq(Inventory::getSkuId, dto.getSkuId()));
        
        if (inventory == null || inventory.getAvailableStock() < dto.getChangeQty()) {
            throw new BusinessException("库存不足");
        }
        
        int beforeQty = inventory.getStock();
        int afterQty = beforeQty - dto.getChangeQty();
        inventory.setStock(afterQty);
        inventory.setAvailableStock(afterQty - inventory.getLockedStock());
        inventoryMapper.updateById(inventory);
        
        createInventoryLog(dto, beforeQty, afterQty);
    }

    @Override
    @Transactional
    public void stockCheck(InventoryDTO dto) {
        Inventory inventory = inventoryMapper.selectOne(new LambdaQueryWrapper<Inventory>()
            .eq(Inventory::getSkuId, dto.getSkuId()));
        
        if (inventory == null) {
            throw new BusinessException("库存记录不存在");
        }
        
        int beforeQty = inventory.getStock();
        int afterQty = dto.getChangeQty();
        inventory.setStock(afterQty);
        inventory.setAvailableStock(afterQty - inventory.getLockedStock());
        inventoryMapper.updateById(inventory);
        
        dto.setChangeQty(afterQty - beforeQty);
        createInventoryLog(dto, beforeQty, afterQty);
    }

    private void createInventoryLog(InventoryDTO dto, int beforeQty, int afterQty) {
        InventoryLog log = new InventoryLog();
        log.setSkuId(dto.getSkuId());
        log.setChangeQty(dto.getChangeQty());
        log.setBeforeQty(beforeQty);
        log.setAfterQty(afterQty);
        log.setType(dto.getType());
        log.setReason(dto.getReason());
        log.setRelatedId(dto.getRelatedId());
        inventoryLogMapper.insert(log);
    }
}
