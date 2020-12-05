package com.lxiaocode.boardgame.auth.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.auth.rbac.domain.PermissionDetails;
import com.lxiaocode.boardgame.auth.rbac.domain.PermissionMapper;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/12/3 下午5:05
 * @blog http://www.lxiaocode.com/
 */
@Service
public class PermissionService extends ServiceImpl<PermissionMapper, PermissionDetails> {

    public void saveOne(PermissionDetails permission) {
        super.save(permission);
    }
}
