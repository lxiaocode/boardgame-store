package com.lxiaocode.boardgame.auth.rbac.controller;

import com.lxiaocode.boardgame.auth.rbac.biz.RoleAction;
import com.lxiaocode.boardgame.auth.rbac.domain.dto.AdditionRoleDTO;
import com.lxiaocode.boardgame.auth.rbac.domain.dto.AdditionRoleToUserDTO;
import com.lxiaocode.boardgame.common.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lixiaofeng
 * @date 2020/12/6 下午5:38
 * @blog http://www.lxiaocode.com/
 */
@RestController
@RequestMapping("/auth/role")
public class RoleController {

    @Autowired
    private RoleAction roleAction;

    @PostMapping("")
    public Result additionRole(@RequestBody @Valid AdditionRoleDTO dto) {
        return roleAction.addition(dto);
    }

    public Result additionRoleToUser(@RequestBody @Valid AdditionRoleToUserDTO dto){
        return roleAction.additionToUser(dto);
    }
}
