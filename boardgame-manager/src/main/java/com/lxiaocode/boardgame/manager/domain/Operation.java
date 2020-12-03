package com.lxiaocode.boardgame.manager.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午3:32
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("manager_permission_operation")
public class Operation extends BaseEntity {
    private static final long serialVersionUID = -1065031677320464138L;

    private String parentId;

    private String name;

    private String url;

    private String note;
}
