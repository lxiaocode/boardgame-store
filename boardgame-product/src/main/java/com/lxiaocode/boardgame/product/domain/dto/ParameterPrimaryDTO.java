package com.lxiaocode.boardgame.product.domain.dto;

import com.lxiaocode.boardgame.product.constant.GameModeEnum;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lixiaofeng
 * @date 2020/12/1 上午8:55
 * @blog http://www.lxiaocode.com/
 */
@Data
public class ParameterPrimaryDTO {
    @NotBlank
    private String productId;

    @Min(value = 1, message = "最小人数不能小于1人")
    private Integer minPerson;

    @Max(value = 25, message = "最大人数不能大于25人")
    private Integer maxPerson;

    @NotNull
    private GameModeEnum mode;

    @Min(value = 1)
    @Max(value = 120)
    private Integer age;

    @NotBlank
    private String year;

    @Min(value = 1)
    @Max(value = 10)
    private Integer difficulty;
}
