package com.lxiaocode.boardgame.auth.exception;

import com.lxiaocode.boardgame.common.response.ApiCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

/**
 * @author lixiaofeng
 * @date 2020/9/6 下午5:10
 * @blog http://www.lxiaocode.com/
 */
@Getter
public class LoginException extends AuthenticationException {
    private static final long serialVersionUID = 495363428023198170L;

    private String code;

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(ApiCode apiCode){
        super(apiCode.getMessage());
        this.code = apiCode.getCode();
    }
}
