package com.lxiaocode.boardgame.auth.rbac.domain;

import com.lxiaocode.boardgame.common.domain.BaseEntity;
import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/12/4 下午5:46
 * @blog http://www.lxiaocode.com/
 */
@Data
public class Operation extends BaseEntity {
    private static final long serialVersionUID = -2251853936201329848L;

    private String name;

    private String url;

    private String note;

    private Integer sort;
}
