package com.lxiaocode.boardgame.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.product.domain.Stockpile;
import com.lxiaocode.boardgame.product.domain.StockpileMapper;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/11/23 下午4:01
 * @blog http://www.lxiaocode.com/
 */
@Service
public class StockpileService extends ServiceImpl<StockpileMapper, Stockpile> {

    public void saveStockpile(Stockpile stockpile) {
        super.save(stockpile);
    }

    public void updateStockpileByProductId(String productId, Stockpile stockpile) {
        super.lambdaUpdate()
                .eq(Stockpile::getProductId, productId)
                .update(stockpile);
    }
}
