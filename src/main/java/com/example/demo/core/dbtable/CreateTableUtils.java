package com.example.demo.core.dbtable;

import com.example.demo.model.TFoodCommonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author: 张帆
 * @create: 2018-12-07 9:23
 * @Description: 创建表工具类
 **/
public class CreateTableUtils {

    private static String DEFAULT_NULL_COMMENT = "DEFAULT NULL COMMENT";


    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * 获取创建表 sql
     * @param dbTableUtils
     * @return
     */
    public static String getSQL(DbTableUtils dbTableUtils){
        StringBuilder sb = new StringBuilder();
        sb.append("create table `"+dbTableUtils.getTableName()+"` ");
        sb.append("(id int(11) NOT NULL COMMENT 'id',");
        sb.append("foodid  "+FiledEnum.getName(3)+"(11) "+CreateTableUtils.DEFAULT_NULL_COMMENT +" '菜品ID'"+ ",");
        sb.append("global "+FiledEnum.getName(3)+"(11) "+CreateTableUtils.DEFAULT_NULL_COMMENT +" '综合'"+ ", ");
        sb.append("score "+FiledEnum.getName(3)+"(2,1)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '评分'"+ ", ");
        sb.append("scorecount "+FiledEnum.getName(2)+"(11)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '评分次数'"+ ", ");
        sb.append("sales "+FiledEnum.getName(3)+"(8)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '销量'"+ ", ");
        sb.append("price "+FiledEnum.getName(3)+"(10,2)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '价格'"+ ", ");
        sb.append("iconaddress "+FiledEnum.getName(1)+"(255)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '菜品图标地址'"+ ", ");
        sb.append("imageaddress "+FiledEnum.getName(1)+"(255)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '菜图片地址'"+ ", ");
        sb.append("foodname "+FiledEnum.getName(1)+"(100)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '菜名'"+ ", ");
        sb.append("sour "+FiledEnum.getName(2)+"(11)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '酸度'"+ ", ");
        sb.append("sweet "+FiledEnum.getName(2)+"(11)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '甜度'"+ ", ");
        sb.append("salty "+FiledEnum.getName(2)+"(11)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '咸度'"+ ", ");
        sb.append("hot "+FiledEnum.getName(2)+"(11)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '辣度'"+ ", ");
        sb.append("authorname "+FiledEnum.getName(1)+"(10)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '作者名'"+ ", ");
        sb.append("authorbrief "+FiledEnum.getName(1)+"(50)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '作者简介'"+ ", ");
        sb.append("authoricon "+FiledEnum.getName(1)+"(255)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '作者图标'"+ ", ");
        sb.append("authordetail "+FiledEnum.getName(1)+"(200)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '作者详情'"+ ", ");
        sb.append("foodbrief "+FiledEnum.getName(1)+"(50)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '菜品简介'"+ ", ");
        sb.append("fooddetail "+FiledEnum.getName(1)+"(200)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '菜品详情'"+ ", ");
        sb.append("matchfoodnum "+FiledEnum.getName(1)+"(255)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '配菜菜品分类号'"+ ", ");
        sb.append("matchfoodid "+FiledEnum.getName(1)+"(255)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '搭配菜品ID'"+ ", ");
        sb.append("foodstat "+FiledEnum.getName(2)+"(11)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '菜品状态'"+ ", ");
        sb.append("jsonpath "+FiledEnum.getName(1)+"(255)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '菜品详情路径'"+ ", ");
        sb.append("foodtype_id "+FiledEnum.getName(2)+"(11)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" 'foodtype_id'"+ ", ");
        sb.append("isdel "+FiledEnum.getName(2)+"(11)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '0 否 1 是'"+ ", ");
        sb.append("food_common_id "+FiledEnum.getName(1)+"(20)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '菜系ID'"+ ", ");
        sb.append("material_id "+FiledEnum.getName(1)+"(20)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '食材ID'"+ ", ");
        sb.append("taste_id "+FiledEnum.getName(1)+"(20)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '味型id'"+ ", ");
        sb.append("foodtype_field "+FiledEnum.getName(1)+"(20)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '菜系字段'"+ ", ");
        sb.append("foodcommon_id "+FiledEnum.getName(2)+"(11)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '菜系id'"+ ", ");
        sb.append("create_time "+FiledEnum.getName(4)+" DEFAULT CURRENT_TIMESTAMP,");
        sb.append("wastetime "+FiledEnum.getName(2)+"(11)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '制作时间'"+ ", ");
        sb.append("version "+FiledEnum.getName(1)+"(255)"+CreateTableUtils.DEFAULT_NULL_COMMENT +" '版本号'"+ ", ");
        sb.append(" primary key(id)  )");
        sb.append(" ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='"+dbTableUtils.getMsg()+"'");
        return sb.toString();
    }


    public static void main(String[] args) {
        DbTableUtils dbTableUtils = new DbTableUtils();
        dbTableUtils.setTableName("0101");
        dbTableUtils.setMsg("表备注");
        String sql = CreateTableUtils.getSQL(dbTableUtils);
        System.out.println(sql);
    }
}
