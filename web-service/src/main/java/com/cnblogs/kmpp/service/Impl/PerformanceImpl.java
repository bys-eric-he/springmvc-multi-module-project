package com.cnblogs.kmpp.service.Impl;

import com.cnblogs.kmpp.service.Performance;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("performance")
public class PerformanceImpl implements Performance {
    private static Logger log = Logger.getLogger(PerformanceImpl.class);

    public void perform() {
        log.info("This is perfom function....");
        System.out.println("This is perfom function....");
    }

    public void perform(int number) {
        log.info("This is perfom(int number) function....");
        System.out.println("This is perfom(int number) function....");
    }
}
