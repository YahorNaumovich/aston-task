package com.example.astontask.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging method executions in service classes.
 * Logs the method name, arguments, and the result of the method execution.
 * Also logs any exceptions thrown during the execution.
 */
@Aspect
@Component
public class ServiceLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLoggingAspect.class);

    /**
     * Pointcut that matches all methods within classes annotated with @Service.
     * This will be used to intercept service methods.
     */
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void servicePointcut() {
        // Pointcut for all services
    }

    /**
     * Logs method execution details for methods in service classes.
     * Logs the method name, arguments, the result of the method, and any exceptions thrown.
     *
     * @param joinPoint the join point that provides details about the method being executed
     * @return the result of the method execution
     * @throws Exception if the method execution fails
     */
    @Around("servicePointcut()")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        logger.info("Executing service method: {} with arguments: {}", methodName, args);

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

