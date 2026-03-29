package com.clothing.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.entity.Member;
import com.clothing.admin.mapper.MemberMapper;
import com.clothing.admin.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public Page<Member> getMemberPage(Integer pageNum, Integer pageSize, String keyword) {
        Page<Member> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
        
        if (StringUtils.hasText(keyword)) {
            wrapper.like(Member::getName, keyword)
                   .or()
                   .like(Member::getPhone, keyword);
        }
        
        wrapper.orderByDesc(Member::getCreateTime);
        return memberMapper.selectPage(page, wrapper);
    }

    @Override
    public Member getMemberById(Long id) {
        return memberMapper.selectById(id);
    }

    @Override
    @Transactional
    public Long createMember(Member member) {
        memberMapper.insert(member);
        return member.getId();
    }

    @Override
    @Transactional
    public void updateMember(Member member) {
        memberMapper.updateById(member);
    }

    @Override
    @Transactional
    public void deleteMember(Long id) {
        memberMapper.deleteById(id);
    }
}
