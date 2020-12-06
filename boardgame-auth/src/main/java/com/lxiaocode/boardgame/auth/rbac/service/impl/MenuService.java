package com.lxiaocode.boardgame.auth.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.auth.rbac.domain.Menu;
import com.lxiaocode.boardgame.auth.rbac.domain.MenuMapper;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/12/6 下午4:02
 * @blog http://www.lxiaocode.com/
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {

    public void saveOne(Menu menu) {
        super.save(menu);
    }

    public boolean checkUnique(String path) {
        Integer count = super.lambdaQuery()
                .eq(Menu::getPath, path)
                .count();

        return count == 0;
    }
}
