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
public class RepositoryLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryLoggingAspect.class);

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryPointcut() {
        // Pointcut for all repositories
    }

    @Around("repositoryPointcut()")
    public Object logRepositoryMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        logger.info("Executing repository method: {} with arguments: {}", methodName, Arrays.toString(args));

        try {
            Object result = joinPoint.proceed();
            logger.info("Repository method {} returned: {}", methodName, result);
            return result;
        } catch (Exception e) {
            logger.error("Error in repository method {}: {}", methodName, e.getMessage());
            throw e;
        }
    }
}
