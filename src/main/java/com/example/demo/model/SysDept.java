package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "sys_dept")
public class SysDept {
    @Id
    private Integer id;

    private Integer pid;

    private String name;

    private Integer sort;

    private Integer isdel;

    private List<SysDept> children;

    public List<SysDept> getChildren() {
        return children;
    }

    public void setChildren(List<SysDept> children) {
        this.children = children;
    }


    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return pid
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return isdel
     */
    public Integer getIsdel() {
        return isdel;
    }

    /**
     * @param isdel
     */
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }


    @Override
    public String toString() {
        return "SysDept{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", isdel=" + isdel +
                ", children=" + children +
                '}';
    }


}