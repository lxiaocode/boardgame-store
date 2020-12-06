package com.lxiaocode.boardgame.auth.rbac.biz;

import com.lxiaocode.boardgame.auth.rbac.PermissionEnum;
import com.lxiaocode.boardgame.auth.rbac.domain.PermissionDetails;
import com.lxiaocode.boardgame.auth.rbac.service.impl.PermissionDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/12/3 下午5:07
 * @blog http://www.lxiaocode.com/
 */
@Service
public class PermissionAction {

    @Autowired
    private PermissionDetailsServiceImpl permissionService;

    public void addition(String resourceId, PermissionEnum type) {
        PermissionDetails permission = new PermissionDetails();
        permission.setResourceId(resourceId);
        permission.setType(type);

        permissionService.saveOne(permission);
    }
}
