package com.lxiaocode.boardgame.common.service;

import com.lxiaocode.boardgame.common.domain.BaseEntity;

import java.util.Optional;

/**
 * @author lixiaofeng
 * @date 2020/12/3 下午3:01
 * @blog http://www.lxiaocode.com/
 */
public interface IUserService<U extends BaseEntity> {

    void saveOne(U user);

    Optional<U> getByUserId(String userId);

    Optional<U> getByUsername(String username);

    boolean checkUnique(U user);
}
