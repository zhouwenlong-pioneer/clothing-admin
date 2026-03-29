package com.clothing.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.entity.Order;
import com.clothing.admin.exception.BusinessException;
import com.clothing.admin.mapper.OrderMapper;
import com.clothing.admin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    public Page<Order> getOrderPage(Integer pageNum, Integer pageSize, Integer status, String keyword) {
        Page<Order> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        
        if (status != null) {
            wrapper.eq(Order::getStatus, status);
        }
        
        if (StringUtils.hasText(keyword)) {
            wrapper.like(Order::getOrderNo, keyword)
                   .or()
                   .like(Order::getReceiverName, keyword);
        }
        
        wrapper.orderByDesc(Order::getCreateTime);
        return orderMapper.selectPage(page, wrapper);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public Order getOrderByNo(String orderNo) {
        return orderMapper.selectOne(new LambdaQueryWrapper<Order>()
            .eq(Order::getOrderNo, orderNo));
    }

    @Override
    @Transactional
    public void deliverOrder(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        
        if (order.getStatus() != 2) {
            throw new BusinessException("订单状态不允许发货");
        }
        
        order.setStatus(3);
        order.setDeliveryTime(LocalDateTime.now());
        orderMapper.updateById(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.selectList(new LambdaQueryWrapper<Order>().orderByDesc(Order::getCreateTime));
    }
}
