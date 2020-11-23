package com.lxiaocode.boardgame.product.biz;

import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.product.domain.Stockpile;
import com.lxiaocode.boardgame.product.domain.dto.StockpileDTO;
import com.lxiaocode.boardgame.product.service.StockpileService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/11/23 下午4:06
 * @blog http://www.lxiaocode.com/
 */
@Service
public class StockpileAction {

    private final StockpileService stockpileService;

    public StockpileAction(StockpileService stockpileService) {
        this.stockpileService = stockpileService;
    }

    public void initStockpile(String productId) {
        Stockpile stockpile = new Stockpile();
        stockpile.setProductId(productId);
        stockpile.setAmount(0);
        stockpile.setFrozen(0);

        stockpileService.saveStockpile(stockpile);
    }

    public Result setStockpile(String productId, StockpileDTO stockpileDTO) {
        Stockpile stockpile = new Stockpile();
        BeanUtils.copyProperties(stockpileDTO, stockpile);
        stockpileService.updateStockpileByProductId(productId, stockpile);

        return Result.success();
    }
}
