package com.lxiaocode.boardgame.admin.security;

import com.lxiaocode.boardgame.admin.security.service.ManagerDetailsService;
import com.lxiaocode.boardgame.auth.config.WebSecurityConfig;
import com.lxiaocode.boardgame.auth.handler.access.RoleBasedAccessDecisionManager;
import com.lxiaocode.boardgame.auth.handler.access.UrlFilterInvocationSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 后台系统服务 Security Config
 * @author lixiaofeng
 * @date 2020/12/2 下午10:43
 * @blog http://www.lxiaocode.com/
 */
@Configuration
public class SecurityConfig extends WebSecurityConfig {

    @Autowired
    private ManagerDetailsService managerDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.managerDetailsService);
    }

    @Override
    protected void WebSecurityConfigure(WebSecurity web) {
        // 忽略 POST 请求
        web.ignoring().antMatchers(
                HttpMethod.POST,
                "/admin/manager/register");
    }

    @Override
    protected void HttpSecurityConfigure(HttpSecurity http) throws Exception {

        // 配置访问控制，鉴权策略
        http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setAccessDecisionManager(new RoleBasedAccessDecisionManager());
                object.setSecurityMetadataSource(new UrlFilterInvocationSecurityMetadataSource());

                return object;
            }
        });

        // 添加过滤器
        http
                .addFilterAt(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(this.managerDetailsService), UsernamePasswordAuthenticationFilter.class);
    }
}
