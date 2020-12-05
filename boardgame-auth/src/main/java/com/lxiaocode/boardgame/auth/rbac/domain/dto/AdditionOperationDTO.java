package com.lxiaocode.boardgame.auth.rbac.domain.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author lixiaofeng
 * @date 2020/12/3 下午3:53
 * @blog http://www.lxiaocode.com/
 */
@Data
public class AdditionOperationDTO {

    @NotBlank(message = "name 不能为空")
    private String name;

    @NotBlank(message = "url 不能为空")
    private String url;

    private String note;

    @Max(value = 65535, message = "sort 不能大于 65535")
    @Min(value = 0, message = "sort 不能小于 0")
    private Integer sort;
}
