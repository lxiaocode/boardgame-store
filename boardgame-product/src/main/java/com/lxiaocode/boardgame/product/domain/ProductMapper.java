package com.lxiaocode.boardgame.product.domain;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxiaocode.boardgame.product.domain.vo.ProductDetailsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午9:07
 * @blog http://www.lxiaocode.com/
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    List<ProductDetailsVO> listProductDetails(int start, int size);
}
