package com.clothing.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.common.PageResult;
import com.clothing.admin.common.Result;
import com.clothing.admin.entity.Member;
import com.clothing.admin.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "会员管理")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "获取会员列表")
    @GetMapping("/page")
    public Result<PageResult<List<Member>>> getPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword) {
        Page<Member> page = memberService.getMemberPage(pageNum, pageSize, keyword);
        PageResult<List<Member>> result = PageResult.of(
            page.getTotal(), page.getPages(), page.getCurrent(), page.getSize(), page.getRecords());
        return Result.success(result);
    }

    @Operation(summary = "获取会员详情")
    @GetMapping("/{id}")
    public Result<Member> getById(@PathVariable Long id) {
        return Result.success(memberService.getMemberById(id));
    }

    @Operation(summary = "创建会员")
    @PostMapping
    public Result<Long> create(@RequestBody Member member) {
        return Result.success(memberService.createMember(member));
    }

    @Operation(summary = "更新会员")
    @PutMapping
    public Result<Void> update(@RequestBody Member member) {
        memberService.updateMember(member);
        return Result.success();
    }

    @Operation(summary = "删除会员")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        memberService.deleteMember(id);
        return Result.success();
    }
}
