package com.common.handle;

import com.common.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/19  21:46
 */
@Slf4j
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {

    /**
     * 参数绑定错误
     * @param request /
     * @param e /
     * @return /
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public Json handleBindException(final BindException e, final HttpServletRequest request) {
        log.warn("参数绑定错误 请求地址:{} msg:{}", request.getRequestURI(), e.getMessage());
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return Json.fail(String.valueOf(400), message);
    }

    /**
     * 参数校验错误
     * @param request /
     * @param e /
     * @return /
     */
    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public Json handleValidationException(final ValidationException e, final HttpServletRequest request) {
        log.warn("参数校验错误 请求地址:{} msg:{}", request.getRequestURI(), e.getMessage());
        return Json.fail(String.valueOf(400), e.getMessage());
    }

    /**
     * Controller参数绑定错误
     * @param e /
     * @param request /
     * @return /
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Json handleMissingServletRequestParameterException(final MissingServletRequestParameterException e,
                                                                 final HttpServletRequest request) {
        log.warn("参数绑定错误 请求地址:{} msg:{}", request.getRequestURI(), e.getMessage());
        return Json.fail(String.valueOf(400), "请求参数不合法");
    }

    /**
     * 未知异常
     * @param request /
     * @param e /
     * @return /
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Json exceptionHandler(final HttpServletRequest request, final Exception e) {
        log.error("未知异常 请求地址:{} ", request.getRequestURI(), e);
        return Json.fail(String.valueOf(500), "服务端异常");
    }
}