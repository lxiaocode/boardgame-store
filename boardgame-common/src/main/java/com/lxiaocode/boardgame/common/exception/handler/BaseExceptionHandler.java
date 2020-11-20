package com.lxiaocode.boardgame.common.exception.handler;

import com.lxiaocode.boardgame.common.response.ApiCode;
import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import com.lxiaocode.boardgame.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author lixiaofeng
 * @date 2020/10/30 下午7:49
 * @blog http://www.lxiaocode.com/
 */
@Slf4j
public class BaseExceptionHandler {
    /**
     * 异常
     * @param e
     * @param request
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception e, HttpServletRequest request) {
        logging(e, request);
        return Result.error(request.getRequestURI(), e.getMessage());
    }

    /**
     * 运行时异常
     * @param e
     * @param request
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public Result runtimeErrorHandler(RuntimeException e, HttpServletRequest request) {
        logging(e, request);
        return Result.error(request.getRequestURI(), e.getMessage());
    }

    /**
     * 参数校验
     * @param e
     * @param request
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result runtimeErrorHandler(ConstraintViolationException e, HttpServletRequest request) {
        logging(e, request);
        String message = "";
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation constraintViolation : constraintViolations){
            message = constraintViolation.getMessage();
        }
        return Result.error(DefaultApiCode.CALIBRATION_FAIL, request.getRequestURI(), message);
    }

    /**
     * 参数校验
     * @param e
     * @param request
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result runtimeErrorHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        logging(e, request);
        StringBuffer stringBuffer = new StringBuffer();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            stringBuffer.append(error.getDefaultMessage()).append(";");
        }
        return Result.error(DefaultApiCode.CALIBRATION_FAIL, request.getRequestURI(), stringBuffer.toString());
    }

    protected void logging(Exception e,HttpServletRequest request){
        if (request == null){
            printLog(e);
        }else {
            printLog(e, request);
        }
    }

    private void printLog(Exception e) {
        log.error("========================= {} =========================", e.getClass().getName());
        e.printStackTrace();
    }

    private void printLog(Exception e,HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();
        log.error("========================= {} =========================", e.getClass().getName());
        log.error("异常接口：({}){}", method, path);
        e.printStackTrace();
    }
}
