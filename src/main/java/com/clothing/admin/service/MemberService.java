package com.clothing.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.entity.Member;

public interface MemberService {
    Page<Member> getMemberPage(Integer pageNum, Integer pageSize, String keyword);
    Member getMemberById(Long id);
    Long createMember(Member member);
    void updateMember(Member member);
    void deleteMember(Long id);
}
