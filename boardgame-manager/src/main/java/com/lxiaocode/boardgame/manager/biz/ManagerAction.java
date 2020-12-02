package com.lxiaocode.boardgame.manager.biz;

import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.manager.constant.StatusEnum;
import com.lxiaocode.boardgame.manager.domain.Manager;
import com.lxiaocode.boardgame.manager.domain.dto.RegisterDTO;
import com.lxiaocode.boardgame.manager.service.ManagerService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午3:40
 * @blog http://www.lxiaocode.com/
 */
@Data
public class ManagerAction {

    @Autowired
    private ManagerService managerService;

    public Result register(RegisterDTO dto) {
        // 校验唯一性
        if (! managerService.checkUniqueManager(dto)){
            return Result.fail(DefaultApiCode.MEMBER_EXISTS);
        }
        Manager manager = new Manager();
        BeanUtils.copyProperties(dto, manager);
        manager.setStatus(StatusEnum.DISENABLED);
        managerService.saveManager(manager);

        return Result.success();
    }
}
