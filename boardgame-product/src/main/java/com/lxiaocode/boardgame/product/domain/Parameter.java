package com.lxiaocode.boardgame.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午10:08
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("product_parameter")
public class Parameter extends BaseEntity {
    private static final long serialVersionUID = 6875304518866978810L;

    private String productId;

    private String item;

    private String value;
}
