package ru.kpfu.itis.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerAdvisor {

     @ExceptionHandler(NoHandlerFoundException.class)
     public String handle() {
        return "404";
    }

    @ExceptionHandler(Exception.class)
    public String handleError()   {
        return "500";
    }
}