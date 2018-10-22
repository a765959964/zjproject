package com.example.demo.core.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author: 张帆
 * @create: 2018-10-19 14:36
 * @Description:
 **/
public class LayuiTree implements Serializable{

    private static final long serialVersionUID = 2966393170143597408L;

    private Integer id;

    private String name;

    private Integer pId;

    private List<LayuiTree>  children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LayuiTree> getChildren() {
        return children;
    }

    public void setChildren(List<LayuiTree> children) {
        this.children = children;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "LayuiTree{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pId=" + pId +
                ", children=" + children +
                '}';
    }
}
