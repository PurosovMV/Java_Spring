package PMV.HW8;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimerAspect {
    @Pointcut("within(@PMV.HW8.Timer *)")
    public void logMethod() {
    }

    @Pointcut("@annotation(PMV.HW8.Timer)")
    public void logWithAnnotation() {
    }

    @Around("logMethod() || logWithAnnotation()")
    public Object loggingMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        Level level = extractLevel(joinPoint);

        log.atLevel(level).log("Метод {} класса {} выполнялся {} ms",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                endTime - startTime);

        return result;
    }

    private Level extractLevel(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Timer annotation = methodSignature.getMethod().getAnnotation(Timer.class);

        if (annotation != null) {
            return annotation.level();
        }
        return joinPoint.getTarget().getClass().getAnnotation(Timer.class).level();
    }
}