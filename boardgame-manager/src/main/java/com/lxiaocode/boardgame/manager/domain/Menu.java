package com.lxiaocode.boardgame.manager.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午3:29
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("manager_permission_menu")
public class Menu extends BaseEntity {
    private static final long serialVersionUID = -6924353540752184882L;

    private String parentId;

    private String name;

    private String path;

    private String component;
}
