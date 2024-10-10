package com.example.astontask.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Aspect for logging method executions in repository classes.
 * Logs the method name, arguments, and the result of the method execution.
 * Also logs any exceptions thrown during the execution.
 */
@Aspect
@Component
public class RepositoryLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryLoggingAspect.class);

    /**
     * Pointcut that matches all methods within classes annotated with @Repository.
     * This will be used to intercept repository methods.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryPointcut() {
        // Pointcut for all repositories
    }

    /**
     * Logs method execution details for methods in repository classes.
     * Logs the method name, arguments, the result of the method, and any exceptions thrown.
     *
     * @param joinPoint the join point that provides details about the method being executed
     * @return the result of the method execution
     * @throws Exception if the method execution fails
     */
    @Around("repositoryPointcut()")
    public Object logRepositoryMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("Executing repository method: {}", methodName);

        try {
            Object result = joinPoint.proceed();
            logger.info("Repository method {} finished", methodName);
            return result;
        } catch (Exception e) {
            logger.error("Error in repository method {}: {}", methodName, e.getMessage());
            throw e;
        }
    }
}
