package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_prefabricate_order")
public class TPrefabricateOrder {
    @Id
    private Integer id;

    /**
     * 门店id
     */
    @Column(name = "kitchen_id")
    private Integer kitchenId;

    /**
     * 菜品id
     */
    private Integer foodid;

    /**
     * 用户id
     */
    private Integer userid;

    @Column(name = "system_sum")
    private Integer systemSum;


    /**
     * 份数
     */
    private Integer sum;

    /**
     * 卖份数
     */
    @Column(name = "sell_num")
    private Integer sellNum;

    /**
     * 剩余份数
     */
    @Column(name = "remain_num")
    private Integer remainNum;

    /**
     * 下单时间
     */
    @Column(name = "pay_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date payDate;

    /**
     * 是否完成（0. 否  1 . 是）
     */
    private Integer status;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 预制时间
     */
    @Column(name = "prefabricate_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private String prefabricateTime;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 1、午餐  2、晚餐
     */
    private Integer type;

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
     * 获取菜品id
     *
     * @return foodid - 菜品id
     */
    public Integer getFoodid() {
        return foodid;
    }

    /**
     * 设置菜品id
     *
     * @param foodid 菜品id
     */
    public void setFoodid(Integer foodid) {
        this.foodid = foodid;
    }

    /**
     * 获取用户id
     *
     * @return userid - 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取份数
     *
     * @return sum - 份数
     */
    public Integer getSum() {
        return sum;
    }

    /**
     * 设置份数
     *
     * @param sum 份数
     */
    public void setSum(Integer sum) {
        this.sum = sum;
    }

    /**
     * 获取卖份数
     *
     * @return sell_num - 卖份数
     */
    public Integer getSellNum() {
        return sellNum;
    }

    /**
     * 设置卖份数
     *
     * @param sellNum 卖份数
     */
    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    /**
     * 获取剩余份数
     *
     * @return remain_num - 剩余份数
     */
    public Integer getRemainNum() {
        return remainNum;
    }

    /**
     * 设置剩余份数
     *
     * @param remainNum 剩余份数
     */
    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    /**
     * 获取下单时间
     *
     * @return pay_date - 下单时间
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置下单时间
     *
     * @param payDate 下单时间
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取是否完成（0. 否  1 . 是）
     *
     * @return status - 是否完成（0. 否  1 . 是）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否完成（0. 否  1 . 是）
     *
     * @param status 是否完成（0. 否  1 . 是）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 获取预制时间
     *
     * @return prefabricate_time - 预制时间
     */
    public String getPrefabricateTime() {
        return prefabricateTime;
    }

    /**
     * 设置预制时间
     *
     * @param prefabricateTime 预制时间
     */
    public void setPrefabricateTime(String prefabricateTime) {
        this.prefabricateTime = prefabricateTime;
    }

    /**
     * 获取销量
     *
     * @return sales - 销量
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * 设置销量
     *
     * @param sales 销量
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * 获取1、午餐  2、晚餐
     *
     * @return type - 1、午餐  2、晚餐
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1、午餐  2、晚餐
     *
     * @param type 1、午餐  2、晚餐
     */
    public void setType(Integer type) {
        this.type = type;
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

    public Integer getSystemSum() {
        return systemSum;
    }

    public void setSystemSum(Integer systemSum) {
        this.systemSum = systemSum;
    }
}