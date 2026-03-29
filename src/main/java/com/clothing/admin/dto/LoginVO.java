package com.clothing.admin.dto;

import lombok.Data;

@Data
public class LoginVO {
    private String token;
    private String username;
    private String nickname;
    private String avatar;
    private String role;
}
