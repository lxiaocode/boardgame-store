package com.lxiaocode.boardgame.auth.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.auth.rbac.domain.IPermissionDetails;
import com.lxiaocode.boardgame.auth.rbac.domain.PermissionDetails;
import com.lxiaocode.boardgame.auth.rbac.domain.PermissionMapper;
import com.lxiaocode.boardgame.auth.rbac.service.PermissionDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/12/3 下午11:56
 * @blog http://www.lxiaocode.com/
 */
@Service
public class PermissionDetailsServiceImpl extends ServiceImpl<PermissionMapper, PermissionDetails> implements PermissionDetailsService {

    public void saveOne(PermissionDetails permission) {
        super.save(permission);
    }

    @Override
    public List<IPermissionDetails> loadPermissionAndRoles() {
        // 连表查询所有
        return new ArrayList<>();
    }
}
