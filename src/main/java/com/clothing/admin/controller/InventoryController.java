package com.clothing.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.common.PageResult;
import com.clothing.admin.common.Result;
import com.clothing.admin.dto.InventoryDTO;
import com.clothing.admin.entity.Inventory;
import com.clothing.admin.entity.InventoryLog;
import com.clothing.admin.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "库存管理")
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @Operation(summary = "获取库存信息")
    @GetMapping("/{skuId}")
    public Result<Inventory> getBySkuId(@PathVariable Long skuId) {
        return Result.success(inventoryService.getInventoryBySkuId(skuId));
    }

    @Operation(summary = "获取库存流水")
    @GetMapping("/log/page")
    public Result<PageResult<List<InventoryLog>>> getLogPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Long skuId) {
        Page<InventoryLog> page = inventoryService.getInventoryLogPage(pageNum, pageSize, skuId);
        PageResult<List<InventoryLog>> result = PageResult.of(
            page.getTotal(), page.getPages(), page.getCurrent(), page.getSize(), page.getRecords());
        return Result.success(result);
    }

    @Operation(summary = "入库")
    @PostMapping("/in")
    public Result<Void> stockIn(@Valid @RequestBody InventoryDTO dto) {
        inventoryService.stockIn(dto);
        return Result.success();
    }

    @Operation(summary = "出库")
    @PostMapping("/out")
    public Result<Void> stockOut(@Valid @RequestBody InventoryDTO dto) {
        inventoryService.stockOut(dto);
        return Result.success();
    }

    @Operation(summary = "盘点")
    @PostMapping("/check")
    public Result<Void> stockCheck(@Valid @RequestBody InventoryDTO dto) {
        inventoryService.stockCheck(dto);
        return Result.success();
    }
}
