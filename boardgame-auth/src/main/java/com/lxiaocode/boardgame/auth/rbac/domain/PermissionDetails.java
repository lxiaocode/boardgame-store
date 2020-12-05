package com.lxiaocode.boardgame.auth.rbac.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.auth.rbac.PermissionEnum;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/12/4 上午11:13
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("manager_permission")
public class PermissionDetails extends BaseEntity implements IPermissionDetails {
    private static final long serialVersionUID = 135744156432112782L;

    private String resourceId;

    private PermissionEnum type;

    @TableField(exist = false)
    private String url;

    @TableField(exist = false)
    private List<IRole> roles;
}
