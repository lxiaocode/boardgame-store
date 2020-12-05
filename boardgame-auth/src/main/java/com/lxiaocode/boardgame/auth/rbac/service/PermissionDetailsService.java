package com.lxiaocode.boardgame.auth.rbac.service;

import com.lxiaocode.boardgame.auth.rbac.domain.IPermissionDetails;

import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/12/3 下午10:27
 * @blog http://www.lxiaocode.com/
 */
public interface PermissionDetailsService {
    List<IPermissionDetails> loadPermissionAndRoles();
}
