package com.lxiaocode.boardgame.manager.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午3:25
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("manager_permission")
public class Permission extends BaseEntity {
    private static final long serialVersionUID = 7787510803515324242L;

    private String resourceId;
}
