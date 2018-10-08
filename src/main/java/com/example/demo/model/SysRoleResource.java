package com.example.demo.model;

import javax.persistence.*;

@Table(name = "sys_role_resource")
public class SysRoleResource {
    @Id
    private Integer id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "resoure_id")
    private Integer resoureId;

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
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return resoure_id
     */
    public Integer getResoureId() {
        return resoureId;
    }

    /**
     * @param resoureId
     */
    public void setResoureId(Integer resoureId) {
        this.resoureId = resoureId;
    }
}