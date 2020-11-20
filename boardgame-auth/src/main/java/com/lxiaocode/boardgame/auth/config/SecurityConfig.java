package com.lxiaocode.boardgame.auth.config;

import com.lxiaocode.boardgame.auth.TokenUtil;
import com.lxiaocode.boardgame.auth.domain.MemberDetails;
import com.lxiaocode.boardgame.auth.exception.LoginException;
import com.lxiaocode.boardgame.auth.filter.JWTAuthenticationFilter;
import com.lxiaocode.boardgame.auth.filter.JsonAuthenticationFilter;
import com.lxiaocode.boardgame.auth.handler.JsonAuthenticationEntryPoint;
import com.lxiaocode.boardgame.auth.service.MemberDetailsService;
import com.lxiaocode.boardgame.common.response.ApiCode;
import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.common.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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

    @Bean
    public JsonAuthenticationEntryPoint jsonAuthenticationEntryPoint() {
        return new JsonAuthenticationEntryPoint();
    }

    /**
     * 处理 JSON 形式登录的过滤器
     * @return
     * @throws Exception
     */
    private JsonAuthenticationFilter jsonAuthenticationFilter() throws Exception {
        JsonAuthenticationFilter jsonAuthenticationFilter = new JsonAuthenticationFilter();

        jsonAuthenticationFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            String userId = ((MemberDetails) authentication.getPrincipal()).getId();
            TokenUtil.Token token = TokenUtil.createToken(userId);

            Result result = Result.success("登录成功").addResult(token);
            ResponseUtil.send(response, result);
        });
        jsonAuthenticationFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            Result result;

            if (exception instanceof LockedException){
                result = Result.fail(DefaultApiCode.LOGIN_FAIL, "账户被锁定");
            }else if (exception instanceof CredentialsExpiredException){
                result = Result.fail(DefaultApiCode.LOGIN_FAIL, "密码过期");
            }else if (exception instanceof AccountExpiredException){
                result = Result.fail(DefaultApiCode.LOGIN_FAIL, "账户过期");
            }else if (exception instanceof DisabledException){
                result = Result.fail(DefaultApiCode.LOGIN_FAIL, "账户被禁用");
            }else if (exception instanceof BadCredentialsException){
                result = Result.fail(DefaultApiCode.LOGIN_FAIL, "用户名或者密码输入错误");
            }else {
                result = Result.fail(DefaultApiCode.LOGIN_FAIL);
            }
            ResponseUtil.send(response, result);
        });

        jsonAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return jsonAuthenticationFilter;
    }

    /**
     * 处理 JWT 形式登录的过滤器
     * @return
     * @throws Exception
     */
    private JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManagerBean());

        jwtAuthenticationFilter.setUserDetailsService(memberDetailsService);
        jwtAuthenticationFilter.setAuthenticationEntryPoint(jsonAuthenticationEntryPoint());

        return jwtAuthenticationFilter;
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

//        web.ignoring().antMatchers(
//                HttpMethod.POST,
//                "/api/member/register");
        web.ignoring().antMatchers(
                HttpMethod.GET,"/admin/token");
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
                .authenticationEntryPoint(jsonAuthenticationEntryPoint())
                .accessDeniedHandler((request, response, exception) -> ResponseUtil.send(response, Result.fail(DefaultApiCode.UNAUTHORIZED)));

        // 添加过滤器
        http.addFilterAt(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
