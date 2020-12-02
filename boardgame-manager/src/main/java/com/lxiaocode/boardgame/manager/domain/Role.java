package com.lxiaocode.boardgame.manager.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import com.lxiaocode.boardgame.manager.constant.StatusEnum;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午3:27
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("manager_role")
public class Role extends BaseEntity {
    private static final long serialVersionUID = 3701229294043390957L;

    private String name;

    private String nameZh;

    private String note;

    private StatusEnum status;
}
