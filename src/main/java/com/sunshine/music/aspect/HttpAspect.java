package com.sunshine.music.aspect;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class HttpAspect {
    @Pointcut("execution(public * com.sunshine.music.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("url={}",request.getRequestURL());
        log.info("method={}",request.getMethod());
        log.info("ip={}",request.getRemoteAddr());
        log.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        log.info("args={}",joinPoint.getArgs());
    }
    @After("log()")
    public void after(){
        log.info("方法执行完毕");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void afterReturning(Object object){
        if (object!=null){
            log.info("response={}",object);
        }
    }
}
