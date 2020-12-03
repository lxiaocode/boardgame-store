package com.lxiaocode.boardgame.manager.biz;

import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.manager.constant.StatusEnum;
import com.lxiaocode.boardgame.manager.domain.Manager;
import com.lxiaocode.boardgame.manager.domain.dto.RegisterDTO;
import com.lxiaocode.boardgame.manager.service.ManagerServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午3:40
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ManagerAction {

    @Autowired
    private ManagerServiceImpl managerServiceImpl;

    public Result register(RegisterDTO dto) {
        Manager manager = new Manager();
        BeanUtils.copyProperties(dto, manager);
        // 校验唯一性
        if (! managerServiceImpl.checkUnique(manager)){
            return Result.fail(DefaultApiCode.MEMBER_EXISTS);
        }

        manager.setPassword(new BCryptPasswordEncoder().encode(manager.getPassword()));
        manager.setStatus(StatusEnum.DISENABLED);
        managerServiceImpl.saveOne(manager);

        return Result.success();
    }
}
