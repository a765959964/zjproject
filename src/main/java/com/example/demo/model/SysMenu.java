package com.example.demo.model;

import javax.persistence.*;

@Table(name = "sys_menu")
public class SysMenu {
    @Id
    private Integer id;

    /**
     * 功能父编号
     */
    private Integer pid;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源url
     */
    private String url;

    private String perms;

    private Integer type;

    private String icon;

    private String remark;

    private Integer status;

    private Integer sort;

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
     * 获取资源名称
     *
     * @return name - 资源名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称
     *
     * @param name 资源名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取资源url
     *
     * @return url - 资源url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置资源url
     *
     * @param url 资源url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * @return perms
     */
    public String getPerms() {
        return perms;
    }

    /**
     * @param perms
     */
    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
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
}