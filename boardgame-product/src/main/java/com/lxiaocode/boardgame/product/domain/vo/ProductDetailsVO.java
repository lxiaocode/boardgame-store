package com.lxiaocode.boardgame.product.domain.vo;

import com.lxiaocode.boardgame.product.constant.ProductStatusEnum;
import com.lxiaocode.boardgame.product.domain.Parameter;
import com.lxiaocode.boardgame.product.domain.Product;
import com.lxiaocode.boardgame.product.domain.Stockpile;
import lombok.Data;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/11/24 下午4:58
 * @blog http://www.lxiaocode.com/
 */
@Setter
public class ProductDetailsVO extends Product{
    private static final long serialVersionUID = -1100377787431723930L;

    private List<ParameterVO> parameters;

    private StockpileVO stockpile;
}
