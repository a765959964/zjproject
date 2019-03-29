package com.example.demo.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_foodtype")
public class TFoodtype {
    /**
     * 序号
     */
    @Id
    private Integer id;

    /**
     * 菜品分类id
     */
    private Integer code;

    /**
     * 菜品名称
     */
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取序号
     *
     * @return id - 序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置序号
     *
     * @param id 序号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取菜品分类id
     *
     * @return code - 菜品分类id
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置菜品分类id
     *
     * @param code 菜品分类id
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取菜品名称
     *
     * @return name - 菜品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜品名称
     *
     * @param name 菜品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}