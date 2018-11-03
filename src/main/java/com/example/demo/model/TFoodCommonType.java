package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "t_food_common_type")
public class TFoodCommonType {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 1代表 一级
2代表 二级
     */
    private Integer level;

    /**
     * 父id
     */
    private String pcode;

    /**
     * 1 .菜系分类    4级
2..食材分类    3级
3. 味型分类    2级
     */
    private Integer status;

    /**
     * 备注
     */
    private Integer isdel;



    private List<TFoodCommonType> children;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取编码
     *
     * @return code - 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编码
     *
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取1代表 一级
2代表 二级
     *
     * @return level - 1代表 一级
2代表 二级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置1代表 一级
2代表 二级
     *
     * @param level 1代表 一级
2代表 二级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取父id
     *
     * @return pcode - 父id
     */
    public String getPcode() {
        return pcode;
    }

    /**
     * 设置父id
     *
     * @param pcode 父id
     */
    public void setPcode(String pcode) {
        this.pcode = pcode == null ? null : pcode.trim();
    }

    /**
     * 获取1 .菜系分类    4级
2..食材分类    3级
3. 味型分类    2级
     *
     * @return status - 1 .菜系分类    4级
2..食材分类    3级
3. 味型分类    2级
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1 .菜系分类    4级
2..食材分类    3级
3. 味型分类    2级
     *
     * @param status 1 .菜系分类    4级
2..食材分类    3级
3. 味型分类    2级
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return isdel - 备注
     */
    public Integer getIsdel() {
        return isdel;
    }

    /**
     * 设置备注
     *
     * @param isdel 备注
     */
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }


    public List<TFoodCommonType> getChildren() {
        return children;
    }

    public void setChildren(List<TFoodCommonType> children) {
        this.children = children;
    }
}