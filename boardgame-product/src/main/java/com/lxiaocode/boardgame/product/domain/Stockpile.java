package com.lxiaocode.boardgame.product.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/11/23 下午3:56
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("product_stockpile")
public class Stockpile extends BaseEntity {
    private static final long serialVersionUID = -5528348379151874395L;

    private String productId;

    private Integer amount;

    private Integer frozen;
}
