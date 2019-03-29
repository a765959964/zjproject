package com.example.demo.model;

import java.util.Date;
import javax.persistence.*;

public class Test {
    @Id
    private Integer id;

    private String name;

    private Integer age;

    private String address;

    @Column(name = "create_tine")
    private Date createTine;

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
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return create_tine
     */
    public Date getCreateTine() {
        return createTine;
    }

    /**
     * @param createTine
     */
    public void setCreateTine(Date createTine) {
        this.createTine = createTine;
    }
}