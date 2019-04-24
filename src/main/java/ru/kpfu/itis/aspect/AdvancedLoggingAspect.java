package ru.kpfu.itis.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AdvancedLoggingAspect {
    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private long start;

    @Pointcut(("execution(* ru.kpfu.itis.service..*.*(..)) && @annotation(MyAnnotation)"))
    public void log(){
    }

    @Before("log()")
    public void logBefore() {
        start = System.nanoTime();
    }

    @After("log()")
    public void logAfter() {
        long result = System.nanoTime() - start;
        logger.info("It takes " + result + " nanoseconds" );
    }

    @AfterReturning("log()")
    public void logSuccess(JoinPoint jp) {
        logger.info("Success with " + jp + ", by " + jp.getTarget().getClass().getSimpleName());
    }

    @AfterThrowing("log()")
    public void logError(JoinPoint jp) {
        logger.error("Error with " + jp + ", by " + jp.getTarget().getClass().getSimpleName());
    }
}
