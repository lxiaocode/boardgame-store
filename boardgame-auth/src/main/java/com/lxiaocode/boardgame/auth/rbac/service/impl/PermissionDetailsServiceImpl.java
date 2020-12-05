package com.lxiaocode.boardgame.auth.rbac.service.impl;

import com.lxiaocode.boardgame.auth.rbac.domain.IPermissionDetails;
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
public class PermissionDetailsServiceImpl implements PermissionDetailsService {

    @Override
    public List<IPermissionDetails> loadPermissionAndRoles() {
        // 连表查询所有
        return new ArrayList<>();
    }
}
