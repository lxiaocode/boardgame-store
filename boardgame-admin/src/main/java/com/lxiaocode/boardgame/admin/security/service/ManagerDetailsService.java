package com.lxiaocode.boardgame.admin.security.service;

import com.lxiaocode.boardgame.auth.domain.SecurityUserDetails;
import com.lxiaocode.boardgame.auth.service.SecurityUserDetailsService;
import com.lxiaocode.boardgame.manager.domain.Manager;
import com.lxiaocode.boardgame.manager.service.ManagerServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lixiaofeng
 * @date 2020/12/3 上午10:08
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ManagerDetailsService implements SecurityUserDetailsService {

    private final ManagerServiceImpl managerServiceImpl;

    public ManagerDetailsService(ManagerServiceImpl managerServiceImpl) {
        this.managerServiceImpl = managerServiceImpl;
    }

    @Override
    public Optional<UserDetails> loadUserByUserId(String userId) {
        return managerServiceImpl.getByUserId(userId).map(manager -> {
            SecurityUserDetails userDetails = new SecurityUserDetails();
            BeanUtils.copyProperties(manager, userDetails);
            // TODO 加载权限
            return userDetails;
        });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = managerServiceImpl.getByUsername(username).orElseGet(Manager::new);

        SecurityUserDetails userDetails = new SecurityUserDetails();
        BeanUtils.copyProperties(manager, userDetails);
        // TODO 加载权限
        return userDetails;
    }
}
