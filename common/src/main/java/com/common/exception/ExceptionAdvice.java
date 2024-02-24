package com.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/2/2  19:46
 */
@RestController
@ControllerAdvice
public class ExceptionAdvice {
    public static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

//    @ResponseBody
//    @ExceptionHandler(SystemException.class)
//    public Json handleException(Exception e) {
//        String code = "";
//        if (e instanceof BusinessException) {
//            code = (((BusinessException) e).getCode());
//        }
//        return Json.fail("系统异常信息：",code,e.getMessage());
//    }

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
//    public Json handleException(RuntimeException e) {
//        String code = "500";
//        return Json.fail("运行异常：",code,e.getMessage());
//    }

//    @ExceptionHandler(BusinessException.class)
//    @ResponseBody
//    public Json doBusinessException(Exception e) {
//        String code = "500";
//        return Json.fail("业务异常：",code,e.getMessage());
//    }

}