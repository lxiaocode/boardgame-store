package com.lxiaocode.boardgame.member.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixiaofeng
 * @date 2020/11/17 下午6:56
 * @blog http://www.lxiaocode.com/
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member")
public class Member extends BaseEntity {
    private static final long serialVersionUID = 3379435242047343646L;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("avatar")
    private String avatar;

    @TableField("telephone")
    private String telephone;

    @TableField("email")
    private String email;
}
