package com.test.devops.aop;

import com.test.devops.aop.annotation.ApiUrl;
import com.test.devops.exception.DevopsExeption;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
@Slf4j
public class LoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointCutWithin() {
    }

    @Pointcut("execution(* com.test.devops.web.rest..**.*(..))")
    public void controllerPointCut() {
    }

    @Pointcut("execution(* com.test.devops.web.rest.ProductResource.*(..))")
    public void productControllerPointCut() {
    }

    @Pointcut("@annotation(com.test.devops.aop.annotation.ApiUrl)")
    public void noParamsAnnotationPointCut() {
    }

    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }

    @Before("controllerPointCut()")
    public void logbeforeControllerPointCut(JoinPoint joinPoint) {
        System.out.println("===============> logbeforeControllerPointCut");
    }

    @After("controllerPointCut()")
    public void logAfterControllerPointCut(JoinPoint joinPoint) {
        System.out.println("===============> logAfterControllerPointCut");
    }

    @Around("controllerPointCut()")
    public Object logAroundControllerPointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("===============> logAroundControllerPointCut");
        return joinPoint.proceed();
    }

//    @AfterThrowing(pointcut = "productControllerPointCut()", throwing = "ex")
//    public void setDevopsExceptionPathField(JoinPoint joinPoint, Throwable ex) {
//        log.error(String.format("Exception from method %s, of class %s caused by %s",
//                joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName(), ex.getMessage()
//        ));
//        log.debug(String.format("Source file: %s, line: %d"
//                , joinPoint.getSourceLocation().getFileName(), joinPoint.getSourceLocation().getLine()
//        ));
//    }

    @Around(value = "productControllerPointCut()")
    public Object logAroundProductControllerPointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("===============> logAroundProductControllerPointCut");
        return joinPoint.proceed();
    }

    @After(value = "controllerPointCutWithin()")
    public void logAfterControllerPointCutWithin(JoinPoint joinPoint) {
        System.out.println("===============> logAfterControllerPointCutWithin");
        logger(joinPoint).error(String.format("Exception from method %s, of class %s",
                joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName()
        ));
    }

    @After(value = "noParamsAnnotationPointCut()")
    public void logAfterAnnotationPointCut(JoinPoint joinPoint) {
        System.out.println("===============> logAfterAnnotationPointCut: " + joinPoint.getSignature().getName());
    }

    @Around(value = "noParamsAnnotationPointCut()")
    public Object logAroundAnnotationPointCut(ProceedingJoinPoint joinPoint) {
        ApiUrl apiUrl = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(ApiUrl.class);
        System.out.println("===============> logAroundAnnotationPointCut of " + joinPoint.getSignature().getName()
                + ", api = " + apiUrl.value());
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("===============> Catch error from annotation-based around aspect");
            logger(joinPoint).error("Error: " + throwable.getMessage());
            throw new DevopsExeption(throwable.getMessage(), throwable.getCause());
        }
    }

}
