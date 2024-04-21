package PMV.timer;



import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimerAspect {
    @Pointcut("@annotation(PMV.timer.Timer)")
    public void methodsAnnotatedWith() {
    }
    @Around("methodsAnnotatedWith()")
    public Object timerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object proceed = joinPoint.proceed();
            long res = System.currentTimeMillis() - start;
            log.info("Класс: {}, метод: {}, время выполнения: {} мс",
                    joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), res);
            return proceed;
        } catch (Throwable e) {
            log.info(e.getMessage());
            throw e;
        }
    }
}
