package com.lxiaocode.boardgame.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/11/30 下午2:56
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("product_parameter_mechanics")
public class Mechanics extends BaseEntity {
    private static final long serialVersionUID = -2800988089004684514L;

    private String productId;

    private String value;
}
