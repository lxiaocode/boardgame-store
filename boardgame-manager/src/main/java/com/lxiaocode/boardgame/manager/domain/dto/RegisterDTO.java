package com.lxiaocode.boardgame.manager.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午3:42
 * @blog http://www.lxiaocode.com/
 */
@Data
public class RegisterDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Email(message = "邮箱格式不合法")
    private String email;
}
