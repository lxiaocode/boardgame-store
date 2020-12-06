package com.lxiaocode.boardgame.auth.rbac.domain.dto;

import lombok.Data;

/**
 * @author lixiaofeng
 * @date 2020/12/6 下午11:12
 * @blog http://www.lxiaocode.com/
 */
@Data
public class AdditionRoleToUserDTO {

    private String userId;

    private String[] roleIds;
}
