package com.lxiaocode.boardgame.common.response;

import lombok.Getter;

/**
 * @author lixiaofeng
 * @date 2020/11/13 下午9:49
 * @blog http://www.lxiaocode.com/
 */
@Getter
public class JsonResult<T> extends Result {
    private static final long serialVersionUID = 1233100515725518805L;

    protected T data;

    protected JsonResult() {
    }

    protected JsonResult(ApiCode code) {
        super(code);
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "data=" + data +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
