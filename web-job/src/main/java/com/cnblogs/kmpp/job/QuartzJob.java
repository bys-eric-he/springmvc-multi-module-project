package com.cnblogs.kmpp.job;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzJob {

    public void work1() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()) + "Quartz的任务调度！！！work1启用……");// new Date()为获取当前系统时间
    }

    public void work2() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()) + "Quartz的任务调度！！！work2启用……");// new Date()为获取当前系统时间
    }
}