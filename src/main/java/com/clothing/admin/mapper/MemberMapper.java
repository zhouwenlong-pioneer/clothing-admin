package com.clothing.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clothing.admin.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
}
