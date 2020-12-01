package com.lxiaocode.boardgame.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.product.constant.ProductStatusEnum;
import com.lxiaocode.boardgame.product.domain.Stockpile;
import com.lxiaocode.boardgame.product.domain.StockpileMapper;
import com.lxiaocode.boardgame.product.domain.vo.StockpileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/11/23 下午4:01
 * @blog http://www.lxiaocode.com/
 */
@Service
public class StockpileService extends ServiceImpl<StockpileMapper, Stockpile> {

    @Autowired
    private StockpileMapper stockpileMapper;

    public void saveStockpile(Stockpile stockpile) {
        super.save(stockpile);
    }

    public void deleteByProductId(String productId) {
        super.lambdaUpdate()
                .eq(Stockpile::getProductId, productId)
                .remove();
    }

    public void updateByProductId(String productId, Stockpile stockpile) {
        super.lambdaUpdate()
                .eq(Stockpile::getProductId, productId)
                .update(stockpile);
    }

    public IPage<StockpileVO> pageStockpile(IPage<StockpileVO> page) {
        return stockpileMapper.pageStockpileVO(page);
    }

    public StockpileVO getStockpileVO(String id) {
        return stockpileMapper.getStockpileVO(id);
    }

    public int getAmountByProductId(String productId) {
        Stockpile one = super.lambdaQuery()
                .eq(Stockpile::getProductId, productId)
                .one();

        return one.getAmount();
    }
}
