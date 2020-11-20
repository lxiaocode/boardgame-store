package com.lxiaocode.boardgame.auth.handler;

import com.lxiaocode.boardgame.auth.exception.LoginException;
import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.common.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lixiaofeng
 * @date 2020/11/20 上午11:01
 * @blog http://www.lxiaocode.com/
 */
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result;
        if (authException instanceof LoginException){
            result = Result.fail(DefaultApiCode.TOKEN_EXPIRED);
        } else {
            result = Result.fail(DefaultApiCode.UNAUTHORIZED, "未登录，无法访问");
        }
        ResponseUtil.send(response, result);
    }
}
