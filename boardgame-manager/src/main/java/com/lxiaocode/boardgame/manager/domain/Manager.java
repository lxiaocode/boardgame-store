package com.lxiaocode.boardgame.manager.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import com.lxiaocode.boardgame.manager.constant.StatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午3:14
 * @blog http://www.lxiaocode.com/
 */
@Data
@TableName("manager")
public class Manager extends BaseEntity {
    private static final long serialVersionUID = -6275049401923635760L;

    private String username;

    private String password;

    private String email;

    private String note;

    private Date loginTime;

    private StatusEnum status;
}
