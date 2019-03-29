package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_kitchen")
public class TKitchen {
    /**
     * 序号
     */
    @Id
    private Integer id;

    /**
     * 门店id
     */
    @Column(name = "kitchen_id")
    private Integer kitchenId;

    /**
     * 门店管理员账号
     */
    @Column(name = "userName")
    private String username;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系人
     */
    private String person;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 用户token
     */
    @Column(name = "user_token")
    private String userToken;

    /**
     * 是否 禁用
0 否 1是
     */
    private Integer status;

    /**
     * 获取序号
     *
     * @return id - 序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置序号
     *
     * @param id 序号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取门店id
     *
     * @return kitchen_id - 门店id
     */
    public Integer getKitchenId() {
        return kitchenId;
    }

    /**
     * 设置门店id
     *
     * @param kitchenId 门店id
     */
    public void setKitchenId(Integer kitchenId) {
        this.kitchenId = kitchenId;
    }

    /**
     * 获取门店管理员账号
     *
     * @return userName - 门店管理员账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置门店管理员账号
     *
     * @param username 门店管理员账号
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取管理员密码
     *
     * @return password - 管理员密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置管理员密码
     *
     * @param password 管理员密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取门店名称
     *
     * @return name - 门店名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置门店名称
     *
     * @param name 门店名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取联系人
     *
     * @return person - 联系人
     */
    public String getPerson() {
        return person;
    }

    /**
     * 设置联系人
     *
     * @param person 联系人
     */
    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取用户token
     *
     * @return user_token - 用户token
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * 设置用户token
     *
     * @param userToken 用户token
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken == null ? null : userToken.trim();
    }

    /**
     * 获取是否 禁用
0 否 1是
     *
     * @return status - 是否 禁用
0 否 1是
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否 禁用
0 否 1是
     *
     * @param status 是否 禁用
0 否 1是
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}