package com.lxiaocode.boardgame.product.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import com.lxiaocode.boardgame.product.constant.ProductStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lixiaofeng
 * @date 2020/11/22 上午11:07
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("product")
public class Product extends BaseEntity {
    private static final long serialVersionUID = 773746755476845497L;

    @TableField("title")
    private String title;

    @TableField("price")
    private Long price;

    @TableField("rate")
    private Float rate;

    @TableField("description")
    private String description;

    @TableField("cover")
    private String cover;

    @TableField("detail")
    private String detail;

    @TableField("status")
    private ProductStatusEnum status;
}
