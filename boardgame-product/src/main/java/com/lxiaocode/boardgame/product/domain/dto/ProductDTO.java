package com.lxiaocode.boardgame.product.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午9:21
 * @blog http://www.lxiaocode.com/
 */
@Data
public class ProductDTO {

    private String title;

    private Long price;

    private Float rate;

    private String description;

    private String cover;

    private String detail;
}
