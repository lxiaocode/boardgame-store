package com.lxiaocode.boardgame.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/11/30 下午2:46
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("product_parameter_category")
public class Category extends BaseEntity {
    private static final long serialVersionUID = 6550004338207802429L;

    private String productId;

    private String value;
}
