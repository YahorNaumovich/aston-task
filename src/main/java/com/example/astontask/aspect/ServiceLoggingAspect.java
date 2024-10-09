package com.example.astontask.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLoggingAspect.class);

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void servicePointcut() {
        // Pointcut for all services
    }

    @Around("servicePointcut()")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        logger.info("Executing service method: {} with arguments: {}", methodName, Arrays.toString(args));

        try {
            Object result = joinPoint.proceed();
            logger.info("Service method {} returned: {}", methodName, result);
            return result;
        } catch (Exception e) {
            logger.error("Error in service method {}: {}", methodName, e.getMessage());
            throw e;
        }
    }
}

