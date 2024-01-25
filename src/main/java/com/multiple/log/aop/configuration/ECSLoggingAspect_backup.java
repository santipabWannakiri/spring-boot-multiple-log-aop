//package com.multiple.log.aop.configuration;
//
//import com.multiple.log.aop.constant.Constants;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.MDC;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//
//@Aspect
//@Component
//@Slf4j
//public class ECSLoggingAspect_backup {
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//
//    @Around("execution(* com.multiple.log.aop.serviceImpl.*.*(..)) || execution(* com.multiple.log.aop.exception.GlobalExceptionHandler.*(..))")
//    public Object ecsLogMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        long startTime = System.currentTimeMillis();
//        String requestId = Constants.getRequestId();
//        MDC.put(Constants.X_REQUEST_ID, requestId);
//
//        //Request
//        MDC.put("event.action", "FCISUHAcc");
//        MDC.put("event.url", "https://example.com/FCISUHAcc/FCISUHAcc");
//        MDC.put("trace.id", "");
//        MDC.put("http.request.id", requestId);
//        MDC.put("kbtg.event.type", "request");
//        MDC.put("kbtg.log.owner", "FCIS");
//        MDC.put("event.start", dateFormat.format(startTime));
//        log.info("example - request");
//
//        //Response
//        Object response;
//        try {
//            response = joinPoint.proceed();
//        } finally {
//            MDC.put("event.action", "FCISUHAcc");
//            MDC.put("event.url", "https://example.com/FCISUHAcc/FCISUHAcc");
//            MDC.put("trace.id", "");
//            MDC.put("http.request.id", requestId);
//            MDC.put("kbtg.event.type", "response");
//            MDC.put("kbtg.log.owner", "FCIS");
//            MDC.put("event.start", dateFormat.format(startTime));
//            long endTime = System.currentTimeMillis();
//            MDC.put("event.end", dateFormat.format(endTime));
//            log.info("example - response");
//
//            MDC.clear();
//        }
//        return response;
//    }
//}
