package com.lxiaocode.boardgame.common.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lixiaofeng
 * @date 2020/11/17 下午6:39
 * @blog http://www.lxiaocode.com/
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 5470770122525970129L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "deleted",fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;
}
