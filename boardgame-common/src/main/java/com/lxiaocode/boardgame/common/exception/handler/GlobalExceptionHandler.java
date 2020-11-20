package com.lxiaocode.boardgame.common.exception.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author lixiaofeng
 * @date 2020/10/30 下午11:00
 * @blog http://www.lxiaocode.com/
 */
@Component
@ControllerAdvice
public class GlobalExceptionHandler extends BaseExceptionHandler {

//    @ResponseBody
//    @ExceptionHandler(value = UserRegisterException.class)
//    public Result runtimeErrorHandler(UserRegisterException e, HttpServletRequest request) {
//        logging(e, request);
//        return Result.fail(e.getCode());
//    }
}
