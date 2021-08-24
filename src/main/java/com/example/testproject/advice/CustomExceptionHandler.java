package com.example.testproject.advice;

import com.example.testproject.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 张昕
 * @Date： 2021/8/23
 * @Time: 17:43
 **/

@ControllerAdvice(basePackages = "com.example.testproject.controller")
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleControllerException(HttpServletRequest request, Throwable ex, Model model) {
        if(ex instanceof CustomException){
            model.addAttribute("message", ex.getMessage());
        }else{
            model.addAttribute("message", "服务冒烟了，待会试试~");
        }
        HttpStatus status = getStatus(request);
        model.addAttribute("status", status);
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        try {
            return HttpStatus.valueOf(code);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}