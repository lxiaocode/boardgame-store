package com.lxiaocode.boardgame.product.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/11/30 下午3:00
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("product_parameter_theme")
public class Theme extends BaseEntity {
    private static final long serialVersionUID = 8712660786108850421L;

    private String productId;

    private String value;
}
