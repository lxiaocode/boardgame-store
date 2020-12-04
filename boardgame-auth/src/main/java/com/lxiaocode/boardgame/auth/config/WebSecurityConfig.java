package com.lxiaocode.boardgame.auth.config;

import com.lxiaocode.boardgame.auth.authentication.filter.JsonAuthenticationFilter;
import com.lxiaocode.boardgame.auth.authentication.filter.JwtAuthenticationFilter;
import com.lxiaocode.boardgame.auth.access.handler.JsonAccessDeniedHandler;
import com.lxiaocode.boardgame.auth.authentication.handler.JsonAuthenticationEntryPoint;
import com.lxiaocode.boardgame.auth.authentication.handler.JsonAuthenticationFailureHandler;
import com.lxiaocode.boardgame.auth.authentication.handler.JwtAuthenticationSuccessHandler;
import com.lxiaocode.boardgame.auth.authentication.SecurityUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午8:57
 * @blog http://www.lxiaocode.com/
 */
public abstract class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // AuthenticationFilter 将忽略以下路径
        web.ignoring().antMatchers("/swagger-ui.html",
                "/swagger-resources/**",
                "/swagger.json",
                "/v2/api-docs/**",
                "/webjars/**",
                "/config/**",
                "/css/**",
                "/fonts/**",
                "/img/**",
                "/image/**",
                "/images/**",
                "/static/**",
                "/js/**");

        this.WebSecurityConfigure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 认证所有请求，关闭CSRF
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .cors();

        // 权限异常处理
        http.exceptionHandling()
                .authenticationEntryPoint(new JsonAuthenticationEntryPoint())
                .accessDeniedHandler(new JsonAccessDeniedHandler());

        this.HttpSecurityConfigure(http);
    }

    /**
     * 处理 JSON 形式登录的过滤器
     * @return 要使用的 JsonAuthenticationFilter
     * @throws Exception
     */
    protected JsonAuthenticationFilter jsonAuthenticationFilter() throws Exception {
        JsonAuthenticationFilter jsonAuthenticationFilter = new JsonAuthenticationFilter();
        jsonAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());

        // 认证处理器
        jsonAuthenticationFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
        jsonAuthenticationFilter.setAuthenticationFailureHandler(new JsonAuthenticationFailureHandler());

        return jsonAuthenticationFilter;
    }

    /**
     * 处理 JWT 形式登录的过滤器
     * @return 要使用的 JwtAuthenticationFilter
     * @throws Exception
     */
    protected JwtAuthenticationFilter jwtAuthenticationFilter(SecurityUserDetailsService userDetailsService) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManagerBean());

        jwtAuthenticationFilter.setUserDetailsService(userDetailsService);
        jwtAuthenticationFilter.setAuthenticationEntryPoint(new JsonAuthenticationEntryPoint());

        return jwtAuthenticationFilter;
    }

    protected void WebSecurityConfigure(WebSecurity web) {}

    protected void HttpSecurityConfigure(HttpSecurity http) throws Exception {}
}
