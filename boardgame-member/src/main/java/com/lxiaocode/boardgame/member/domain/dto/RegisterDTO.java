package com.lxiaocode.boardgame.member.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author lixiaofeng
 * @date 2020/11/19 下午6:47
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

    @Pattern(regexp = "1\\d{10}", message = "手机号格式不正确")
    private String telephone;
}
