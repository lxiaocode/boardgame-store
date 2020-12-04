package com.lxiaocode.boardgame.auth.rbac.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/12/4 上午11:07
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("manager_role")
public class Role extends BaseEntity implements IRole {
    private static final long serialVersionUID = 875607971167123149L;

    private String name;

    private String nameZh;

    private String note;
}
