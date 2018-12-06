package com.example.demo;

import com.example.demo.model.SysDept;
import com.example.demo.service.RedisService;
import com.example.demo.service.SysDeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RedisService redisService;

    @Autowired
    SysDeptService  sysDeptService;

	@Test
	public void contextLoads() {
   /*     List<SysDept> sysDeptList = sysDeptService.getAll(null);
        Map<String,String> map = new HashMap<>();
        for (SysDept sysDept :sysDeptList){
            map.put("id",sysDept.getId()+"");
            map.put("name",sysDept.getName());
            map.put("sort",sysDept.getSort()+"");
            redisService.lpush("sys_dept:"+sysDept.getId(),map);
        }*/
        String s = redisService.get("sys_dept:16");
        System.out.println("取值"+s);
    }


}
