package com.example.demo.model;

import javax.persistence.*;

@Table(name = "sys_resource")
public class SysResource {
    @Id
    private Integer id;

    /**
     * 功能父编号
     */
    private Integer pid;

    private String name;

    private String url;

    private String remark;

    private Integer status;

    private String menu;

    private Integer sort;

    /**
     * 对应shiro权限
     */
    @Column(name = "resource_inil")
    private String resourceInil;

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
     * 获取功能父编号
     *
     * @return pid - 功能父编号
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置功能父编号
     *
     * @param pid 功能父编号
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
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return menu
     */
    public String getMenu() {
        return menu;
    }

    /**
     * @param menu
     */
    public void setMenu(String menu) {
        this.menu = menu == null ? null : menu.trim();
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
     * 获取对应shiro权限
     *
     * @return resource_inil - 对应shiro权限
     */
    public String getResourceInil() {
        return resourceInil;
    }

    /**
     * 设置对应shiro权限
     *
     * @param resourceInil 对应shiro权限
     */
    public void setResourceInil(String resourceInil) {
        this.resourceInil = resourceInil == null ? null : resourceInil.trim();
    }
}