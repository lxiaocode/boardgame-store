package com.lxiaocode.boardgame.auth.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.auth.rbac.domain.Role;
import com.lxiaocode.boardgame.auth.rbac.domain.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/12/6 下午4:41
 * @blog http://www.lxiaocode.com/
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    public void saveOne(Role role) {
        super.save(role);
    }

    public boolean checkUnique(String roleName) {
        Integer count = super.lambdaQuery()
                .eq(Role::getName, roleName)
                .count();

        return count == 0;
    }
}
