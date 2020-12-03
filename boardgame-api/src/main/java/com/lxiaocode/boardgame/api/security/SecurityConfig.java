package com.lxiaocode.boardgame.api.security;

import com.lxiaocode.boardgame.api.security.service.MemberDetailsService;
import com.lxiaocode.boardgame.auth.config.WebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 前台服务 Security Config
 * @author lixiaofeng
 * @date 2020/11/17 下午7:20
 * @blog http://www.lxiaocode.com/
 */
@Configuration
public class SecurityConfig extends WebSecurityConfig {

    private final MemberDetailsService memberDetailsService;

    public SecurityConfig(MemberDetailsService memberDetailsService) {
        this.memberDetailsService = memberDetailsService;
    }

    /**
     * 配置 AuthenticationManager
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.memberDetailsService);
    }

    @Override
    protected void WebSecurityConfigure(WebSecurity web) {
        // 忽略 POST 请求
        web.ignoring().antMatchers(
                HttpMethod.POST,
                "/api/member/register");
    }

    @Override
    protected void HttpSecurityConfigure(HttpSecurity http) throws Exception {
        // 添加过滤器
        http
                .addFilterAt(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(memberDetailsService), UsernamePasswordAuthenticationFilter.class);
    }
}
