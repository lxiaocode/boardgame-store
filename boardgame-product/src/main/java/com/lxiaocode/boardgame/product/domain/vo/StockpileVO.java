package com.lxiaocode.boardgame.product.domain.vo;

import com.lxiaocode.boardgame.product.domain.Stockpile;
import lombok.Data;
import lombok.Setter;

/**
 * @author lixiaofeng
 * @date 2020/11/24 下午5:06
 * @blog http://www.lxiaocode.com/
 */
@Data
public class StockpileVO extends Stockpile {
    private static final long serialVersionUID = 885412096742259423L;

    private String title;
}
