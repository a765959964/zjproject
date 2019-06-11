package com.example.demo;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.core.dbtable.CreateTableUtils;
import com.example.demo.core.dbtable.DbTableUtils;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RedisService redisService;

    @Autowired
    SysDeptService  sysDeptService;

//    @Test
    public void getFoodList(){
        String sql = "select * from v_foodtable t  order by t.id  ";
//        String sql = " ";
        List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(sql);
        if(queryForList.size()>0){
            for(Map<String,Object> m:queryForList){
                String jsonpath = m.get("jsonpath").toString();
                Object result = HttpUtil.get("http://"+jsonpath);
                JSONObject jsonObject = JSONObject.parseObject(result.toString());
                String kw  =  jsonObject.get("KW").toString();
                JSONObject jo = JSONObject.parseObject(kw);
                String field = jsonObject.getString("CPCXFL");
                String table =  "t"+field.substring(0,4);
                String foodid = jsonObject.getString("CPID");
                String  sour = jo.get("S").toString();
                String  hot = jo.get("L").toString();
                String  salty = jo.get("X").toString();
                String  sweet = jo.get("T").toString();
                String updSql = "update " + table + " set sour=?,hot=?,salty=?,sweet=? where foodid=?";
                int count = jdbcTemplate.update(updSql,new Object[]{sour,hot,salty,sweet,foodid});
                System.out.println(count);
            }
        }
    }


//    @Test
    public void getKitList(){
        String kitchenId ="";
        String sql = "select * from t_kitchen";
        List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
        if(queryForList.size() > 0){
            for (Map<String,Object> m:queryForList) {
                kitchenId =   m.get("kitchen_id")+"";
                String kitName =  m.get("name")+"";
                System.out.println("门店id是："+kitchenId+",门店名称"+kitName);
            }
        }
    }



//    @Test
    public void getList(){
       String sql = "select t.`code`,t.`name` from t_food_common_type t where t.`level` = 2 and t.`status` = 1 and t.isdel =0";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> l : list ){
            DbTableUtils dbTableUtils = new DbTableUtils();
            dbTableUtils.setTableName("t"+l.get("code").toString());
            dbTableUtils.setMsg(l.get("name").toString());
            String sb =  CreateTableUtils.getSQL(dbTableUtils);
//            jdbcTemplate.execute(sb);
        }

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
