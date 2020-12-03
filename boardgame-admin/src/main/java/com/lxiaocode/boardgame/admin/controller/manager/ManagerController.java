package com.lxiaocode.boardgame.admin.controller.manager;

import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.manager.biz.ManagerAction;
import com.lxiaocode.boardgame.manager.domain.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午4:15
 * @blog http://www.lxiaocode.com/
 */
@RestController
@RequestMapping("/admin/manager")
public class ManagerController {

    @Autowired
    private ManagerAction managerAction;

    @PostMapping("/register")
    public Result register(@RequestBody @Valid RegisterDTO registerDTO) {
        return managerAction.register(registerDTO);
    }
}
