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
@SuppressWarnings("unused")
public class TaskLoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(TaskLoggingAspect.class);

    @Pointcut("execution(public * chevron81.barebone.task..*(..))")
    @SuppressWarnings("unused")
    private void publicMethodsFromController() {
    }

    @Around("publicMethodsFromController()")
    @SuppressWarnings("unused")
    public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();
        final Object[] args = joinPoint.getArgs();
        final String methodSignature = joinPoint.getSignature().toString();
        LOGGER.info("TASK {} started", methodSignature);
        final Object result = joinPoint.proceed();
        final long end = System.currentTimeMillis();
        LOGGER.info("TASK {} finished in {} ms", methodSignature, end - start);
        return result;
    }
}
