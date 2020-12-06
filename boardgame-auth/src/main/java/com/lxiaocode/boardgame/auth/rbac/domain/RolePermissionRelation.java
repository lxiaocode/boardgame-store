package com.lxiaocode.boardgame.auth.rbac.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/12/3 下午8:44
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("manager_role_permission_relation")
public class RolePermissionRelation extends BaseEntity {
    private static final long serialVersionUID = -2581085977763752968L;

    private String roleId;

    private String permissionId;
}
