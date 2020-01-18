package com.example.demo.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_food_work")
public class TFoodWork {
    @Id
    private Integer id;

    /**
     * 门店id
     */
    @Column(name = "kitchen_id")
    private Integer kitchenId;

    /**
     * 自助餐id
     */
    @Column(name = "food_work_id")
    private Integer foodWorkId;

    /**
     * 菜品名称
     */
    private String foodname;

    /**
     * 1.工作餐  2自助餐 3自助餐候补餐
     */
    private Integer type;

    /**
     * 工作餐时间
     */
    @Column(name = "work_date")
    private Date workDate;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取自助餐id
     *
     * @return food_work_id - 自助餐id
     */
    public Integer getFoodWorkId() {
        return foodWorkId;
    }

    /**
     * 设置自助餐id
     *
     * @param foodWorkId 自助餐id
     */
    public void setFoodWorkId(Integer foodWorkId) {
        this.foodWorkId = foodWorkId;
    }

    /**
     * 获取菜品名称
     *
     * @return foodname - 菜品名称
     */
    public String getFoodname() {
        return foodname;
    }

    /**
     * 设置菜品名称
     *
     * @param foodname 菜品名称
     */
    public void setFoodname(String foodname) {
        this.foodname = foodname == null ? null : foodname.trim();
    }

    /**
     * 获取1.工作餐  2自助餐 3自助餐候补餐
     *
     * @return type - 1.工作餐  2自助餐 3自助餐候补餐
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1.工作餐  2自助餐 3自助餐候补餐
     *
     * @param type 1.工作餐  2自助餐 3自助餐候补餐
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取工作餐时间
     *
     * @return work_date - 工作餐时间
     */
    public Date getWorkDate() {
        return workDate;
    }

    /**
     * 设置工作餐时间
     *
     * @param workDate 工作餐时间
     */
    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
}