package com.lxiaocode.boardgame.product.domain;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lxiaocode.boardgame.product.domain.vo.ParameterVO;
import com.lxiaocode.boardgame.product.domain.vo.StockpileVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lixiaofeng
 * @date 2020/11/23 下午3:58
 * @blog http://www.lxiaocode.com/
 */
@Mapper
public interface StockpileMapper extends BaseMapper<Stockpile> {
    IPage<StockpileVO> pageStockpile(IPage<StockpileVO> page);
}
