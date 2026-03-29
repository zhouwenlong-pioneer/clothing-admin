package com.clothing.admin.controller;

import com.clothing.admin.common.Result;
import com.clothing.admin.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "数据统计")
@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final OrderService orderService;
    private final ProductService productService;
    private final MemberService memberService;
    private final InventoryService inventoryService;

    @Operation(summary = "获取首页看板数据")
    @GetMapping("/stats")
    public Result<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // 订单统计
        stats.put("totalOrders", orderService.getAllOrders().size());
        
        // 商品统计
        stats.put("totalProducts", productService.getAllProducts().size());
        
        // 会员统计
        stats.put("totalMembers", 100); // 简化
        
        // 库存预警
        stats.put("lowStockCount", 5); // 简化
        
        // 销售金额
        stats.put("totalSales", new BigDecimal("10000.00"));
        
        return Result.success(stats);
    }
}
