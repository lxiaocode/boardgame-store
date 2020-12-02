package com.lxiaocode.boardgame.manager.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.manager.domain.Manager;
import com.lxiaocode.boardgame.manager.domain.ManagerMapper;
import com.lxiaocode.boardgame.manager.domain.dto.RegisterDTO;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午3:48
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ManagerService extends ServiceImpl<ManagerMapper, Manager> {

    public void saveManager(Manager manager) {
        super.save(manager);
    }

    public boolean checkUniqueManager(RegisterDTO dto) {
        Integer count = super.lambdaQuery()
                .eq(Manager::getUsername, dto.getUsername())
                .or()
                .eq(Manager::getEmail, dto.getEmail())
                .count();

        return count == 0;
    }
}
