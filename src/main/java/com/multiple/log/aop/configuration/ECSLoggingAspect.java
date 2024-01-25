package com.multiple.log.aop.configuration;

import com.multiple.log.aop.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Aspect
@Component
@Slf4j
public class ECSLoggingAspect {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final ThreadLocal<String> requestIdThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> traceIdThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Long> eventStartThreadLocal = new ThreadLocal<>();

    @Before("execution(* com.multiple.log.aop.serviceImpl.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        String requestId = Constants.getRequestId();
        requestIdThreadLocal.set(requestId);
        eventStartThreadLocal.set(startTime);
        initializeCommonMDCFields("FCISUHAcc", "https://example.com/FCISUHAcc/FCISUHAcc", "response", "FCIS");
        log.info("Request log to ECS");
    }

    @AfterReturning(pointcut = "execution(* com.multiple.log.aop.serviceImpl.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        initializeCommonMDCFields("FCISUHAcc", "https://example.com/FCISUHAcc/FCISUHAcc", "response", "FCIS");
        MDC.put("event.end", dateFormat.format(System.currentTimeMillis()));
        log.info("Response log to ECS");
        MDC.clear();
        requestIdThreadLocal.remove();
        eventStartThreadLocal.remove();
    }

    @AfterThrowing(pointcut = "execution(* com.multiple.log.aop.serviceImpl.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        initializeCommonMDCFields("FCISUHAcc", "https://example.com/FCISUHAcc/FCISUHAcc", "response", "FCIS");
        MDC.put("event.end", dateFormat.format(System.currentTimeMillis()));
        log.error("Exception in {}: {}", joinPoint.getSignature().toShortString(), ex.getMessage(), ex);
        MDC.clear();
        requestIdThreadLocal.remove();
        eventStartThreadLocal.remove();
    }

    private void initializeCommonMDCFields(String action, String url, String type, String owner) {
        MDC.put("event.action", action);
        MDC.put("event.url", url);
        MDC.put("trace.id", traceIdThreadLocal.get());
        MDC.put("http.request.id", requestIdThreadLocal.get());
        MDC.put("kbtg.event.type", type);
        MDC.put("kbtg.log.owner", owner);
        MDC.put("event.start", dateFormat.format(eventStartThreadLocal.get()));
    }

}
