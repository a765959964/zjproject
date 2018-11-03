package com.example.demo.dto;

import sun.reflect.generics.tree.Tree;

import java.util.List;

/**
 * @author: 张帆
 * @create: 2018-11-02 16:16
 * @Description: 组装树形菜单
 **/
public class TreeDto {

    private Integer id;

    private String code;

    private String name;

    private String pcode;

    private Integer level;

    private List<TreeDto> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public List<TreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDto> children) {
        this.children = children;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
