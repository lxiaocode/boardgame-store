package com.lxiaocode.boardgame.member.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author lixiaofeng
 * @date 2020/11/17 下午7:03
 * @blog http://www.lxiaocode.com/
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member_wallet")
public class Wallet extends BaseEntity {
    private static final long serialVersionUID = 5813552765208114539L;

    @TableField("member_id")
    private String memberId;

    @TableField("money")
    private BigDecimal money;

    @TableField("froze")
    private BigDecimal froze;
}
