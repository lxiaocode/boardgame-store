package com.lxiaocode.boardgame.auth.authentication.handler;

import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.common.util.ResponseUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午9:39
 * @blog http://www.lxiaocode.com/
 */
public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

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
    }
}
