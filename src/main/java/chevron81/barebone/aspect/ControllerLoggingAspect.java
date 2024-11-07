package chevron81.barebone.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect {

    private static final Logger logger = LogManager.getLogger(ControllerLoggingAspect.class);

    @Pointcut("execution(public * chevron81.barebone.controller..*(..))")
    @SuppressWarnings("unused")
    private void publicMethodsFromController() {
    }

    @Around("publicMethodsFromController()")
    @SuppressWarnings("unused")
    public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();
        final Object[] args = joinPoint.getArgs();
        final String methodSignature = joinPoint.getSignature().toString();
        logger.info(">>> {} - ARGS: {}", methodSignature, args);
        final Object result = joinPoint.proceed();
        final long end = System.currentTimeMillis();
        logger.info("<<< Duration: {} ms {} - RESULT: {}", end - start, methodSignature, result);
        return result;
    }
}