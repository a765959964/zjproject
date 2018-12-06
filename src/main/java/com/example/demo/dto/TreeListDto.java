package com.example.demo.dto;

import com.example.demo.core.utils.TreeList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 张帆
 * @create: 2018-12-03 15:07
 * @Description:
 **/
public class TreeListDto implements  Serializable {


    private static final long serialVersionUID = -2058052052958525187L;

    private Integer id;
    private String name;
    private String alias;
    private String palias;


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


    @Override
    public String toString() {
        return "TreeListDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", palias='" + palias + '\'' +
                '}';
    }
}
