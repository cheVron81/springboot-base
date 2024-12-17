package chevron81.base.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@SuppressWarnings("unused")
public class ControllerLoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(ControllerLoggingAspect.class);

    @Pointcut("execution(public * chevron81.base.api.controller..*(..))")
    @SuppressWarnings("unused")
    private void publicMethodsFromController() {
    }

    @Around("publicMethodsFromController()")
    @SuppressWarnings("unused")
    public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();
        final Object[] args = joinPoint.getArgs();
        final String methodSignature = joinPoint.getSignature().toString();
        ControllerLoggingAspect.LOGGER.info(">>> {} - ARGS: {}", methodSignature, args);
        final Object result = joinPoint.proceed();
        final long end = System.currentTimeMillis();
        ControllerLoggingAspect.LOGGER.info("<<< Duration: {} ms {} - RESULT: {}", end - start, methodSignature, result);
        return result;
    }
}