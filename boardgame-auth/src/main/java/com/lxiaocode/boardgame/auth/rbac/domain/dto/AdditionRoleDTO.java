package com.lxiaocode.boardgame.auth.rbac.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lixiaofeng
 * @date 2020/12/3 下午8:18
 * @blog http://www.lxiaocode.com/
 */
@Data
public class AdditionRoleDTO {

    @NotBlank(message = "name 不可为空")
    private String name;

    @NotBlank(message = "nameZh 不可为空")
    private String nameZh;

    private String note;

    private String[] permissionIds;
}
