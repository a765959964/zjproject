package com.example.demo.model;

import java.math.BigDecimal;
import javax.persistence.*;

public class User {
    @Id
    private Long id;

    private Integer age;

    private BigDecimal banlance;

    private String name;

    private String username;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return banlance
     */
    public BigDecimal getBanlance() {
        return banlance;
    }

    /**
     * @param banlance
     */
    public void setBanlance(BigDecimal banlance) {
        this.banlance = banlance;
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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}