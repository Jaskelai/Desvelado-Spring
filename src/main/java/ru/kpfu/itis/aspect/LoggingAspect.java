package ru.kpfu.itis.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterReturning("execution(* ru.kpfu.itis.service..*.*(..))")
    public void logSuccess(JoinPoint jp) {
        logger.info("Success with " + jp);
    }

    @AfterThrowing("execution(* ru.kpfu.itis.service..*.*(..))")
    public void logError(JoinPoint jp) {
        logger.error("Error with " + jp);
    }
}
