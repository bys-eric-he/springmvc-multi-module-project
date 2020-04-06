package com.cnblogs.kmpp.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class PerformanceCounter {
    private static Logger log = Logger.getLogger(Audience.class);
    private Map<Integer, Integer> trackCounts = new HashMap<Integer, Integer>();

    @Pointcut("execution(* com.cnblogs.kmpp.service.Performance.perform(int)) && args(number)")
    public void performAsked(int number) {
    }

    @Before("performAsked(number)")
    public void countAsked(int number) {
        int currentCount = getAskedCount(number);
        trackCounts.put(number, currentCount + 1);
    }

    private int getAskedCount(int number) {
        log.info("asked count is " + number);
        return trackCounts.containsKey(number) ? trackCounts.get(number) : 0;
    }
}
