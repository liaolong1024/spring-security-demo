package com.lot.ccsmsb.aspect;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ll
 */
@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /** 以@WebLog注解为切点 */
    @Pointcut("@annotation(com.lot.ccsmsb.annotation.WebLog)")
    public void webLog(){}

    /** 在切点之前织入 */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws JsonProcessingException {
        ServletRequestAttributes reqAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = reqAttributes.getRequest();

        logger.info("============================================Start============================================");
        logger.info("URL: {}", request.getRequestURL().toString());
        logger.info("HTTP Method: {}", request.getMethod());
        // controller的全路径以及执行方法
        logger.info("Class Method: {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
        logger.info("IP: {}", request.getRemoteAddr());
        logger.info("Request Args: {}", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }

    @After("webLog()")
    public void doAfter() {
        logger.info("============================================End============================================");
    }


    // Around通知“proceed之前的代码”在Before之前执行， “proceed之后的代码”在After之后执行
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String traceId = "TRACE_ID";
        MDC.put(traceId, IdWorker.getIdStr());
        logger.info("进入Around通知");
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        logger.info("Response Args: {}", new ObjectMapper().writeValueAsString(result));
        logger.info("Time-Consuming: {} ms", System.currentTimeMillis()-startTime);
        MDC.remove(traceId);
        return result;
    }

}
