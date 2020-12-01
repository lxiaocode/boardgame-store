package com.lxiaocode.boardgame.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.product.domain.Theme;
import com.lxiaocode.boardgame.product.domain.ThemeMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author lixiaofeng
 * @date 2020/11/30 下午3:47
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ThemeService extends ServiceImpl<ThemeMapper, Theme> {

    public void batchSaveTheme(Set<Theme> themes) {
        super.saveBatch(themes, themes.size());
    }

    public void deleteByProductId(String productId) {
        super.lambdaUpdate()
                .eq(Theme::getProductId, productId)
                .remove();
    }
}
