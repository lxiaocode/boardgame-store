package com.lxiaocode.boardgame.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.product.domain.ParameterPrimary;
import com.lxiaocode.boardgame.product.domain.ParameterPrimaryMapper;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/11/30 下午3:38
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ParameterPrimaryService extends ServiceImpl<ParameterPrimaryMapper, ParameterPrimary> {

    public void saveParameterPrimary(ParameterPrimary parameterPrimary) {
        super.save(parameterPrimary);
    }

    public void deletedByProductId(String productId) {
        super.lambdaUpdate()
                .eq(ParameterPrimary::getProductId, productId)
                .remove();
    }

    public void updateByProductId(ParameterPrimary parameterPrimary) {
        super.lambdaUpdate()
                .eq(ParameterPrimary::getProductId, parameterPrimary.getProductId())
                .update(parameterPrimary);
    }
}
