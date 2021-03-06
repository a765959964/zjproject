package com.example.demo.model;


import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.*;

@Table(name = "sys_role")
public class SysRole {
    /**
     * 角色名称
     */
    @Id
    private Integer id;

    /**
     * 角色名称，用于显示
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "role_desc")
    private String roleDesc;

    /**
     * 角色值，用于权限判断
     */
    @Column(name = "role_value")
    private String roleValue;

    @Column(name = "create_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否禁用
     */
    @Column(name = "is_disable")
    private Integer isDisable;

    private List<Long> menuIds;


    /**
     * 获取角色名称
     *
     * @return id - 角色名称
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置角色名称
     *
     * @param id 角色名称
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色名称，用于显示
     *
     * @return role_name - 角色名称，用于显示
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称，用于显示
     *
     * @param roleName 角色名称，用于显示
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 获取角色描述
     *
     * @return role_desc - 角色描述
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * 设置角色描述
     *
     * @param roleDesc 角色描述
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    /**
     * 获取角色值，用于权限判断
     *
     * @return role_value - 角色值，用于权限判断
     */
    public String getRoleValue() {
        return roleValue;
    }

    /**
     * 设置角色值，用于权限判断
     *
     * @param roleValue 角色值，用于权限判断
     */
    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue == null ? null : roleValue.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取是否禁用
     *
     * @return is_disable - 是否禁用
     */
    public Integer getIsDisable() {
        return isDisable;
    }

    /**
     * 设置是否禁用
     *
     * @param isDisable 是否禁用
     */
    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

}