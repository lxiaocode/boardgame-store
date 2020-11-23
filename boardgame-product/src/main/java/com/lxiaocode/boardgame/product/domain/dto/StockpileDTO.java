package com.lxiaocode.boardgame.product.domain.dto;

import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/11/23 下午4:22
 * @blog http://www.lxiaocode.com/
 */
@Data
public class StockpileDTO {
    private Integer amount;

    private Integer frozen;
}
