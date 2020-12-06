package com.lxiaocode.boardgame.auth.rbac.domain;

import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/12/6 下午3:57
 * @blog http://www.lxiaocode.com/
 */
@Data
public class Menu extends BaseEntity {
    private static final long serialVersionUID = -844413671645504186L;

    private String parentId;

    private String name;

    private String path;

    private String component;
}
