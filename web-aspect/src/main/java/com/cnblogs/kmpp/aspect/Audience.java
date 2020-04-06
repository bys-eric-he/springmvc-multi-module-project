package com.cnblogs.kmpp.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class Audience {
    private String methodName = null;
    private Object[] inputParam = null;
    private Object outputParam = null;
    private static Logger log = Logger.getLogger(Audience.class);
    /**
     * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
     * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
     * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
     * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
     * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
     * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
     */
    ObjectMapper mapper = new ObjectMapper();

    @Pointcut("execution(* com.cnblogs.kmpp.service.Performance.perform(..))")
    //com.cnblogs.kmpp.service.Performance 接口任意方法的执行都加入该切点
    //@Pointcut("execution(* com.cnblogs.kmpp.service.Performance.*(..))")
    //实现了 Performance 接口的目标对象的任意连接点
    //@Pointcut("target(com.cnblogs.kmpp.service.Performance)")
    public void performance() {
    }

    @Before("performance()")
    public void silenceCellPhones(JoinPoint joinPoint) {
        try {
            Object args[] = joinPoint.getArgs();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            System.out.println("function Name is " + method.getName());
            System.out.println("function Arguments is " + mapper.writeValueAsString(args));
            log.info("@Before(\"execution(@com.cnblogs.kmpp.service.Performance.perform(..))\")");
            System.out.println("Silencing cell phones");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Around("performance()")
    public Object watch(ProceedingJoinPoint joinPoint) {
        try {
            methodName = joinPoint.getSignature().toLongString();
            inputParam = joinPoint.getArgs();
            System.out.println("Method->".concat(methodName).concat("Parameter->").concat(String.valueOf(inputParam)));
            outputParam = joinPoint.proceed();
            log.info("@Around(\"execution(@com.cnblogs.kmpp.service.Performance.perform(..))\")");
            System.out.println("clap !");
        } catch (Throwable e) {
            System.out.println("clap !");
        }
        return outputParam;
    }

    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        methodName = joinPoint.getSignature().toLongString();
        inputParam = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        log.info("AOP Start->".concat("the function started at->" + startTime + " time"));
        try {
            outputParam = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("function->".concat(methodName), e);
        }

        long endTime = System.currentTimeMillis();
        log.info("AOP End->".concat("the function take->" + (endTime - startTime) + " ms"));
        return outputParam;
    }

    @AfterReturning("performance())")
    public void applause(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("functionName is " + method.getName());
        log.info("@AfterReturning(\"execution(@com.cnblogs.kmpp.service.Performance.perform(..))\")");
        System.out.println("clap clap clap!!");
    }

    @AfterThrowing("performance())")
    public void demandRefund(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("functionName is " + method.getName());
        System.out.println("Demanding a refund!");
    }
}
