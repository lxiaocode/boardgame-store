package com.lxiaocode.boardgame.auth.rbac.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lixiaofeng
 * @date 2020/12/3 下午6:07
 * @blog http://www.lxiaocode.com/
 */
@Data
public class AdditionMenuDTO {

    private String parentId;

    @NotBlank(message = "name 不能为空")
    private String name;

    @NotBlank(message = "path 不能为空")
    private String path;

    @NotBlank(message = "component 不能为空")
    private String component;
}
