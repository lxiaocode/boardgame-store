package com.lxiaocode.boardgame.auth.rbac.domain;

import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/12/3 下午10:28
 * @blog http://www.lxiaocode.com/
 */
public interface IPermissionDetails {

    List<IRole> getRoles();

    String getUrl();
}
