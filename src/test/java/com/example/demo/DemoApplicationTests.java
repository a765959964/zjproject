package com.example.demo;

import com.example.demo.core.dbtable.CreateTableUtils;
import com.example.demo.core.dbtable.DbTableUtils;
import com.example.demo.model.TFoodCommonType;
import com.example.demo.service.RedisService;
import com.example.demo.service.SysDeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public void getList(){
     /*   String sql = "select t.`code`,t.`name` from t_food_common_type t where t.`level` = 2 and t.`status` = 1 and t.isdel =0";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> l : list ){
            DbTableUtils dbTableUtils = new DbTableUtils();
            dbTableUtils.setTableName(l.get("code").toString());
            dbTableUtils.setMsg(l.get("name").toString());
            String sb =  CreateTableUtils.getSQL(dbTableUtils);
            jdbcTemplate.execute(sb);
        }*/

    }

	@Test
	public void contextLoads() {

	    StringBuilder sb = new StringBuilder();

	    sb.append("create table aa1");
	    sb.append("(id int(11) NOT NULL AUTO_INCREMENT,name varchar(80), ");
	    sb.append("age int,address varchar(220),");
	    sb.append(" primary key(id)  )");
        System.out.println(sb.toString());
//        jdbcTemplate.execute(sb.toString());
    }


}
