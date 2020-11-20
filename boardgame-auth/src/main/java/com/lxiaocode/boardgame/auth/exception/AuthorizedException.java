package com.lxiaocode.boardgame.auth.exception;

import com.lxiaocode.boardgame.common.response.ApiCode;
import lombok.Getter;

/**
 * @author lixiaofeng
 * @date 2020/9/6 下午5:30
 * @blog http://www.lxiaocode.com/
 */
@Getter
public class AuthorizedException extends RuntimeException {

    private String code;

    public AuthorizedException() {
    }

    public AuthorizedException(String message) {
        super(message);
    }

    public AuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizedException(Throwable cause) {
        super(cause);
    }

    public AuthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AuthorizedException(ApiCode apiCode){
        super(apiCode.getMessage());
        this.code = apiCode.getCode();
    }
}
