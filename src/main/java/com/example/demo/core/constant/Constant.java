package com.example.demo.core.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 读取application 自定义文件 装备到bean中
 * @author: 张帆
 * @create: 2018-11-08 10:29
 * @Description:
 **/
@Component
public class Constant {

    @Autowired
    private Environment env;



    public int pageSize(){
       return Integer.parseInt(env.getProperty("pageSize"));
    }

    public String path(){
        return env.getProperty("system-path");
    }




}
