package com.lxiaocode.boardgame.product.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午10:57
 * @blog http://www.lxiaocode.com/
 */
@Data
public class ParameterDTO {

    @NotBlank(message = "item 不能为空")
    private String item;

    @NotBlank(message = "value 不能为空")
    private String value;
}
