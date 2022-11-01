package ru.sber.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    private final Logger log = Logger.getLogger(this.getClass());

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void pointcutRestController() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestControllerAdvice *)")
    public void pointcutRestControllerAdvice() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Around("pointcutRestController() || pointcutRestControllerAdvice()")
    public Object adviceRestController(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object result = null;
        try {
            log.info("Start method: " + signature.getName());
            result = joinPoint.proceed();
            log.info("Finish method with result: " + result);
            return result;
        } catch (Throwable t) {
            log.error(t.getMessage());
        }
        return result;
    }
}
