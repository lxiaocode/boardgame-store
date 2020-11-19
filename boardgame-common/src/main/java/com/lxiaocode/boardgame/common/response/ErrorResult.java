package com.lxiaocode.boardgame.common.response;

import lombok.Getter;

/**
 * 异常信息数据返回格式
 * @author lixiaofeng
 * @date 2020/10/30 下午7:18
 * @blog http://www.lxiaocode.com/
 */
@Getter
public class ErrorResult extends Result {
    private static final long serialVersionUID = -6539789803068932673L;

    private final String path;

    private final String exceptionMsg;

    protected ErrorResult(ApiCode code, String path, String exceptionMsg) {
        super(code);
        this.path = path;
        this.exceptionMsg = exceptionMsg;
    }

    @Override
    public String toString() {
        return "ErrorResult{" +
                "path='" + path + '\'' +
                ", exceptionMsg='" + exceptionMsg + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
