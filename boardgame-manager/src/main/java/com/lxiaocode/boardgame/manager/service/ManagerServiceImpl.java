package com.lxiaocode.boardgame.manager.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.common.service.IUserService;
import com.lxiaocode.boardgame.manager.domain.Manager;
import com.lxiaocode.boardgame.manager.domain.ManagerMapper;
import com.lxiaocode.boardgame.manager.domain.dto.RegisterDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午3:48
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IUserService<Manager> {

    @Override
    public void saveOne(Manager user) {
        super.save(user);
    }

    @Override
    public Optional<Manager> getByUserId(String userId) {
        return Optional.ofNullable(super.getById(userId));
    }

    @Override
    public Optional<Manager> getByUsername(String username) {
        return super.lambdaQuery()
                .eq(Manager::getUsername, username)
                .oneOpt();
    }

    @Override
    public boolean checkUnique(Manager user) {
        Integer count = super.lambdaQuery()
                .eq(Manager::getUsername, user.getUsername())
                .or()
                .eq(Manager::getEmail, user.getEmail())
                .count();

        return count == 0;
    }
}
