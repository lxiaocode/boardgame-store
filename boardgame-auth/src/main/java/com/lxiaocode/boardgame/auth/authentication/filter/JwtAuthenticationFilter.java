package com.lxiaocode.boardgame.auth.authentication.filter;

import com.lxiaocode.boardgame.auth.TokenUtil;
import com.lxiaocode.boardgame.auth.exception.LoginException;
import com.lxiaocode.boardgame.auth.authentication.SecurityUserDetailsService;
import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lixiaofeng
 * @date 2020/11/20 上午10:47
 * @blog http://www.lxiaocode.com/
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private AuthenticationEntryPoint authenticationEntryPoint;
    private SecurityUserDetailsService securityUserDetailsService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = TokenUtil.getTokenByRequest(request);
        if (StringUtils.isEmpty(token) || !token.startsWith(TokenUtil.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        String userId = "";
        try {
            userId = TokenUtil.getUserIdByToken(token).orElseThrow(() -> new LoginException(DefaultApiCode.TOKEN_EXPIRED));
        }catch (AuthenticationException e){
            this.authenticationEntryPoint.commence(request, response, e);
        }

        securityUserDetailsService.loadUserByUserId(userId).ifPresent(userDetails -> {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // 存入 SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        });

        chain.doFilter(request, response);
    }

    public void setUserDetailsService(SecurityUserDetailsService securityUserDetailsService) {
        this.securityUserDetailsService = securityUserDetailsService;
    }

    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }
}
