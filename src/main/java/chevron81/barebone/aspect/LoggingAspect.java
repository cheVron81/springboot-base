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
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Pointcut("execution(public * chevron81.app.*.*(..))")
    private void publicMethodsFromChevron81() {
    }

    @Around(value = "publicMethodsFromChevron81()")
    public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Object[] args = joinPoint.getArgs();
        final String methodName = joinPoint.getSignature().getName();
        LoggingAspect.logger.debug(">> {}() - {}", methodName, args);
        final Object result = joinPoint.proceed();
        LoggingAspect.logger.debug("<< {}() - {}", methodName, result);
        return result;
    }
}
