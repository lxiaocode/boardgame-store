package com.lxiaocode.boardgame.auth.authentication.handler;

import com.lxiaocode.boardgame.auth.TokenUtil;
import com.lxiaocode.boardgame.auth.authentication.SecurityUserDetails;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.common.util.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午9:28
 * @blog http://www.lxiaocode.com/
 */
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String userId = ((SecurityUserDetails) authentication.getPrincipal()).getId();
        TokenUtil.Token token = TokenUtil.createToken(userId);

        Result result = Result.success("登录成功").addResult(token);
        ResponseUtil.send(response, result);
    }
}
