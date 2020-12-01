package com.lxiaocode.boardgame.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.product.domain.Mechanics;
import com.lxiaocode.boardgame.product.domain.MechanicsMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author lixiaofeng
 * @date 2020/11/30 下午3:45
 * @blog http://www.lxiaocode.com/
 */
@Service
public class MechanicsService extends ServiceImpl<MechanicsMapper, Mechanics> {

    public void batchSaveMechanics(Set<Mechanics> mechanics) {
        super.saveBatch(mechanics, mechanics.size());
    }

    public void deleteByProductId(String productId) {
        super.lambdaUpdate()
                .eq(Mechanics::getProductId, productId)
                .remove();
    }
}
