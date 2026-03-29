package com.clothing.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clothing.admin.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {
}
