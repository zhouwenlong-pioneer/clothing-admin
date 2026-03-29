package com.clothing.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clothing.admin.dto.LoginDTO;
import com.clothing.admin.dto.LoginVO;
import com.clothing.admin.entity.User;

public interface UserService {
    LoginVO login(LoginDTO loginDTO);
    User getUserById(Long id);
    Page<User> getUserPage(Integer pageNum, Integer pageSize, String keyword);
}
