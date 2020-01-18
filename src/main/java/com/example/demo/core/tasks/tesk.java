package com.example.demo.core.tasks;

import com.example.demo.core.constant.Constant;
import com.example.demo.service.TFoodCommonTypeService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
/**
 * 开启定时任务的注解
 */
@EnableScheduling
public class tesk {

    @Resource
    private TFoodCommonTypeService tFoodCommonTypeService;

    @Resource
    private Constant constant;

    //@Scheduled(fixedRate = 5000)
    public void job1(){
        System.out.println("定时任务1" + new Date());
    }

  /*  @Scheduled(cron = "0 30 08 * * ?")
    public void job2(){
        System.out.println("8点30 执行");
        tFoodCommonTypeService.createJsonAndFile();
        System.out.println("执行结束"+new Date(System.currentTimeMillis()));
    }*/
}
