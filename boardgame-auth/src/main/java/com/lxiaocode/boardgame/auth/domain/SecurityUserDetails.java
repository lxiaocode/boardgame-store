package com.lxiaocode.boardgame.auth.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午9:31
 * @blog http://www.lxiaocode.com/
 */
@Data
public class SecurityUserDetails implements UserDetails {
    private static final long serialVersionUID = 3398843678869098976L;

    private String id;
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // 账号是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 账号凭证是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 帐号是否被锁定
    @Override
    public boolean isEnabled() {
        return true;
    }
}
