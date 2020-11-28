package com.lxiaocode.boardgame.product.domain.vo;

import com.lxiaocode.boardgame.product.domain.Stockpile;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

/**
 * @author lixiaofeng
 * @date 2020/11/24 下午5:06
 * @blog http://www.lxiaocode.com/
 */
@Data
public class StockpileVO {

    private String id;

    private String productId;

    private String productTitle;

    private Integer amount;

    private Integer frozen;

    private Date createTime;
}
