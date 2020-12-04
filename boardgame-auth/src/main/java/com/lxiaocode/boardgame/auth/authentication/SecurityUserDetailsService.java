package com.lxiaocode.boardgame.auth.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午5:13
 * @blog http://www.lxiaocode.com/
 */
public interface SecurityUserDetailsService extends UserDetailsService {

    Optional<UserDetails> loadUserByUserId(String userId);
}
