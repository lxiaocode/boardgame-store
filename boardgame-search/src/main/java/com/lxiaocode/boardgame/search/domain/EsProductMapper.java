package com.lxiaocode.boardgame.search.domain;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/11/28 上午10:36
 * @blog http://www.lxiaocode.com/
 */
public interface EsProductMapper extends BaseMapper<EsProduct> {
    List<EsProduct> allEsProduct();

    List<EsProduct> getProduct();
}
