package com.example.demo.core.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 张帆
 * @create: 2018-12-03 13:25
 * @Description:
 **/
public class TreeList implements  Serializable{

    private Integer id;
    private String name;
    private String alias;
    private String palias;

    private List<TreeList> list = new ArrayList<>();

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPalias() {
        return palias;
    }

    public void setPalias(String palias) {
        this.palias = palias;
    }

    public List<TreeList> getList() {
        return list;
    }

    public void setList(List<TreeList> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "TreeList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", palias='" + palias + '\'' +
                ", list=" + list +
                '}';
    }
}
