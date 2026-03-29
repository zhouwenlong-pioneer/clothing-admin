package com.clothing.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.common.PageResult;
import com.clothing.admin.common.Result;
import com.clothing.admin.entity.Order;
import com.clothing.admin.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "订单管理")
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "获取订单列表")
    @GetMapping("/page")
    public Result<PageResult<List<Order>>> getPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String keyword) {
        Page<Order> page = orderService.getOrderPage(pageNum, pageSize, status, keyword);
        PageResult<List<Order>> result = PageResult.of(
            page.getTotal(), page.getPages(), page.getCurrent(), page.getSize(), page.getRecords());
        return Result.success(result);
    }

    @Operation(summary = "获取订单详情")
    @GetMapping("/{id}")
    public Result<Order> getById(@PathVariable Long id) {
        return Result.success(orderService.getOrderById(id));
    }

    @Operation(summary = "根据订单号获取订单")
    @GetMapping("/no/{orderNo}")
    public Result<Order> getByNo(@PathVariable String orderNo) {
        return Result.success(orderService.getOrderByNo(orderNo));
    }

    @Operation(summary = "订单发货")
    @PostMapping("/{id}/deliver")
    public Result<Void> deliver(@PathVariable Long id) {
        orderService.deliverOrder(id);
        return Result.success();
    }

    @Operation(summary = "获取所有订单")
    @GetMapping("/list")
    public Result<List<Order>> list() {
        return Result.success(orderService.getAllOrders());
    }
}
