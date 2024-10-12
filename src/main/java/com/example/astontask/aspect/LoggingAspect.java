package com.example.astontask.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Pointcut that matches all methods within classes annotated with @RestController, @Service.
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || " +
            "within(@org.springframework.stereotype.Service *)")
    public void loggingPointcut() {
        // Pointcut for all controllers and services
    }

    /**
     * Logs the method name, arguments, the result of the method, and any exceptions thrown.
     *
     * @param joinPoint the join point that provides details about the method being executed
     * @return the result of the method execution
     * @throws Throwable if the method execution fails
     */
    @Around("loggingPointcut()")
    public Object logMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        String componentType = getComponentType(joinPoint);

        logger.info("Executing {} method: {} with arguments: {}", componentType, methodName, args);

        try {
            Object result = joinPoint.proceed();
            logger.info("{} method {} returned: {}", componentType, methodName, result);
            return result;
        } catch (Throwable e) {
            logger.error("Error in {} method {}: {}", componentType, methodName, e.getMessage());
            throw e;
        }
    }

    /**
     * Helper method to determine the type of component (Controller, Service).
     *
     * @param joinPoint the join point
     * @return the component type as a String
     */
    private String getComponentType(ProceedingJoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();

        if (targetClass.isAnnotationPresent(RestController.class)) {
            return "Controller";
        } else if (targetClass.isAnnotationPresent(org.springframework.stereotype.Service.class)) {
            return "Service";
        }
        return "Unknown";
    }

}
