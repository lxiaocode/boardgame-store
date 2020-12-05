package com.lxiaocode.boardgame.auth.rbac.controller;

import com.lxiaocode.boardgame.auth.rbac.biz.OperationAction;
import com.lxiaocode.boardgame.auth.rbac.domain.dto.AdditionOperationDTO;
import com.lxiaocode.boardgame.common.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lixiaofeng
 * @date 2020/12/5 下午10:36
 * @blog http://www.lxiaocode.com/
 */
@RestController
@RequestMapping("/auth/rbac/operation")
public class RbacController {
    @Autowired
    private OperationAction operationAction;

    @PostMapping("")
    public Result additionOperation(@RequestBody @Valid AdditionOperationDTO dto) {
        return operationAction.addition(dto);
    }
}
