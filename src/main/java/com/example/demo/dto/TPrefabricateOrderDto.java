package com.example.demo.dto;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by 张帆 on 2019/11/6.
 */
public class TPrefabricateOrderDto {
    @Id
    private Integer id;

    /**
     * 门店id
     */
    private Integer kitchenId;

    /**
     * 菜品id
     */
    private Integer foodid;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 份数
     */
    private Integer sum;

    /**
     * 卖份数
     */
    private Integer sellNum;

    /**
     * 剩余份数
     */
    private Integer remainNum;

    /**
     * 下单时间
     */
    private Date payDate;

    /**
     * 是否完成（0. 否  1 . 是）
     */
    private Integer status;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 预制时间
     */
    private String prefabricateTime;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 1、午餐  2、晚餐
     */
    private Integer type;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(Integer kitchenId) {
        this.kitchenId = kitchenId;
    }

    public Integer getFoodid() {
        return foodid;
    }

    public void setFoodid(Integer foodid) {
        this.foodid = foodid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getSellNum() {
        return sellNum;
    }

    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPrefabricateTime() {
        return prefabricateTime;
    }

    public void setPrefabricateTime(String prefabricateTime) {
        this.prefabricateTime = prefabricateTime;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
