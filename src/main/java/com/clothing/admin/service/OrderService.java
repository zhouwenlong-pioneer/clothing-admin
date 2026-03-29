package com.clothing.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.entity.Order;
import java.util.List;

public interface OrderService {
    Page<Order> getOrderPage(Integer pageNum, Integer pageSize, Integer status, String keyword);
    Order getOrderById(Long id);
    Order getOrderByNo(String orderNo);
    void deliverOrder(Long orderId);
    List<Order> getAllOrders();
}
