package com.lxiaocode.boardgame.auth.config;

import com.lxiaocode.boardgame.auth.filter.JsonAuthenticationFilter;
import com.lxiaocode.boardgame.auth.service.MemberDetailsService;
import com.lxiaocode.boardgame.common.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author lixiaofeng
 * @date 2020/11/17 下午7:20
 * @blog http://www.lxiaocode.com/
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberDetailsService memberDetailsService;

    public SecurityConfig(MemberDetailsService memberDetailsService) {
        this.memberDetailsService = memberDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public JsonAuthenticationFilter jsonAuthenticationFilter() throws Exception {
        JsonAuthenticationFilter jsonAuthenticationFilter = new JsonAuthenticationFilter();

        jsonAuthenticationFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            // TODO 登录成功处理
            ResponseUtil.send(response, "登录成功");
        });
        jsonAuthenticationFilter.setAuthenticationFailureHandler((request, response, authentication) -> {
            // TODO 登录失败处理
            ResponseUtil.send(response, "登录失败");
        });

        jsonAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return jsonAuthenticationFilter;
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider memberAuthenticationProvider = new DaoAuthenticationProvider();
        memberAuthenticationProvider.setUserDetailsService(memberDetailsService);
        memberAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        auth.authenticationProvider(memberAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 认证所有请求，关闭CSRF
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        http.addFilterAt(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
