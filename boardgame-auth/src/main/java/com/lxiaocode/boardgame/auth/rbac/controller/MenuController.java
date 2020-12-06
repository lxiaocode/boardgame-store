package com.lxiaocode.boardgame.auth.rbac.controller;

import com.lxiaocode.boardgame.auth.rbac.biz.MenuAction;
import com.lxiaocode.boardgame.auth.rbac.domain.dto.AdditionMenuDTO;

import com.lxiaocode.boardgame.common.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lixiaofeng
 * @date 2020/12/6 下午4:28
 * @blog http://www.lxiaocode.com/
 */
@RestController
@RequestMapping("/auth/menu")
public class MenuController {

    @Autowired
    private MenuAction menuAction;

    @PostMapping("")
    public Result additionMenu(@RequestBody @Valid AdditionMenuDTO dto) {
        return menuAction.addition(dto);
    }
}
