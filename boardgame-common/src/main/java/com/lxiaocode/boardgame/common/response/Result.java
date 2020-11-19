package com.lxiaocode.boardgame.common.response;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lixiaofeng
 * @date 2020/11/13 下午9:32
 * @blog http://www.lxiaocode.com/
 */
@Getter
public class Result implements Serializable {
    private static final long serialVersionUID = 5618822730928399367L;

    private static final Result SUCCESS = new Result(DefaultApiCode.SUCCESS);
    private static final Result FAIL = new Result(DefaultApiCode.FAIL);

    protected String code;
    protected String message;

    protected Result() {}

    protected Result(ApiCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static Result success() {
        return SUCCESS;
    }

    public static Result success(String msg) {
        Result result = new Result(DefaultApiCode.SUCCESS);
        result.message = msg;
        return result;
    }

    public static Result fail() {
        return FAIL;
    }

    public static Result fail(ApiCode code) {
        return new Result(code);
    }

    public static Result fail(ApiCode code, String msg) {
        Result result = new Result(code);
        result.message = msg;
        return result;
    }

    public <T> JsonResult<T> addResult(T data) {
        JsonResult<T> jsonResult = new JsonResult<T>();
        jsonResult.code = this.code;
        jsonResult.message = this.message;
        jsonResult.data = data;
        return jsonResult;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
