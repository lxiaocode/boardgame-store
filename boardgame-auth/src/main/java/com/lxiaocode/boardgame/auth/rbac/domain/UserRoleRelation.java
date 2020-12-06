package com.lxiaocode.boardgame.auth.rbac.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/12/6 下午10:58
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("manager_user_role_relation")
public class UserRoleRelation extends BaseEntity {
    private static final long serialVersionUID = -3463423264441931572L;

    private String userId;

    private String roleId;
}
