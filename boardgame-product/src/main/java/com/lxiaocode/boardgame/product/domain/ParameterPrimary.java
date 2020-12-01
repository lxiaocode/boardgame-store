package com.lxiaocode.boardgame.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import com.lxiaocode.boardgame.product.constant.GameModeEnum;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/11/30 下午2:30
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("product_parameter_primary")
public class ParameterPrimary extends BaseEntity {
    private static final long serialVersionUID = 515263498884420562L;

    private String productId;

    private Integer minPerson;

    private Integer maxPerson;

    private GameModeEnum mode;

    private Integer age;

    private String year;

    private Integer difficulty;
}
