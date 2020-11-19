package com.lxiaocode.boardgame.common.response;

/**
 * @author lixiaofeng
 * @date 2020/11/13 下午9:40
 * @blog http://www.lxiaocode.com/
 */
public enum DefaultApiCode implements ApiCode {
    SUCCESS("0", "执行成功"),
    FAIL("-1", "系统发生异常，请稍后重试"),

    LOGIN_FAIL("A0200", "登录失败异常"),
    TOKEN_AUTHENTICATION_FAIL("A0220", "用户身份校验失败"),
    TOKEN_EXPIRED("A0230", "用户登录已过期"),;

    private String code;
    private String message;

    DefaultApiCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
