package com.example.astontask.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging method executions in controllers.
 * Logs the method name, arguments, and the result of the method execution.
 * Also logs any exceptions thrown during the execution.
 */
@Aspect
@Component
public class ControllerLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLoggingAspect.class);

    /**
     * Pointcut that matches all methods within classes annotated with @RestController.
     * This will be used to intercept controller methods.
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointcut() {
        // Pointcut for all controllers
    }

    /**
     * Logs method execution details for methods in controllers.
     * Logs the method name, arguments, the result of the method, and any exceptions thrown.
     *
     * @param joinPoint the join point that provides details about the method being executed
     * @return the result of the method execution
     * @throws Exception if the method execution fails
     */
    @Around("controllerPointcut()")
    public Object logControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        logger.info("Executing controller method: {} with arguments: {}", methodName, args);

        try {
            Object result = joinPoint.proceed();
            logger.info("Controller method {} returned: {}", methodName, result);
            return result;
        } catch (Exception e) {
            logger.error("Error in controller method {}: {}", methodName, e.getMessage());
            throw e;
        }
    }
}

