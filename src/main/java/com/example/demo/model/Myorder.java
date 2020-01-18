package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Myorder {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 订单ID
     */
    private BigDecimal orderid;

    /**
     * 用户ID
     */
    private Long userid;

    /**
     * 总价款
     */
    private BigDecimal totalprice;

    /**
     * 订单时间(外卖的配送时间)
     */
    private Date ordertime;

    /**
     * 订单状态
1、待支付
2、待收货
3、待评价
4、退款
5、完成
7、已付款
     */
    private Integer state;

    /**
     * 优惠金额
     */
    private BigDecimal discountprice;

    /**
     * 菜品种类总数
     */
    private Integer foodtypecount;

    /**
     * 菜品ID/份数/单价
     */
    private String foodinfo;

    /**
     * 订单描述
     */
    private String descript;

    /**
     * 预约编号
     */
    private BigDecimal reserve;

    /**
     * 门店id
     */
    @Column(name = "kitchen_id")
    private Long kitchenId;

    /**
     * 订单类型
1、.预定
2、排序
3、现场点餐
4、九成菜
5、聚餐

     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 餐台点赞
0.未点赞
1.已点赞
     */
    @Column(name = "center_service")
    private Integer centerService;

    /**
     * 前厅点赞
0.未点赞
1.已点赞
     */
    @Column(name = "hall_service")
    private Integer hallService;

    /**
     * 推送：
0.待推送
1.推送待确认
2.已推送
     */
    @Column(name = "is_push")
    private Integer isPush;

    /**
     * 订单完成时间
     */
    @Column(name = "over_time")
    private Date overTime;

    /**
     * 制作状态
1：待制作
2、已导入平板
3：制作中
4：制作完
     */
    @Column(name = "product_status")
    private Integer productStatus;

    /**
     * 是否打印:
1,已打印；
2，未打印
     */
    @Column(name = "isPrint")
    private Integer isprint;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单ID
     *
     * @return orderid - 订单ID
     */
    public BigDecimal getOrderid() {
        return orderid;
    }

    /**
     * 设置订单ID
     *
     * @param orderid 订单ID
     */
    public void setOrderid(BigDecimal orderid) {
        this.orderid = orderid;
    }

    /**
     * 获取用户ID
     *
     * @return userid - 用户ID
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置用户ID
     *
     * @param userid 用户ID
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 获取总价款
     *
     * @return totalprice - 总价款
     */
    public BigDecimal getTotalprice() {
        return totalprice;
    }

    /**
     * 设置总价款
     *
     * @param totalprice 总价款
     */
    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * 获取订单时间(外卖的配送时间)
     *
     * @return ordertime - 订单时间(外卖的配送时间)
     */
    public Date getOrdertime() {
        return ordertime;
    }

    /**
     * 设置订单时间(外卖的配送时间)
     *
     * @param ordertime 订单时间(外卖的配送时间)
     */
    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    /**
     * 获取订单状态
1、待支付
2、待收货
3、待评价
4、退款
5、完成
7、已付款
     *
     * @return state - 订单状态
1、待支付
2、待收货
3、待评价
4、退款
5、完成
7、已付款
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置订单状态
1、待支付
2、待收货
3、待评价
4、退款
5、完成
7、已付款
     *
     * @param state 订单状态
1、待支付
2、待收货
3、待评价
4、退款
5、完成
7、已付款
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取优惠金额
     *
     * @return discountprice - 优惠金额
     */
    public BigDecimal getDiscountprice() {
        return discountprice;
    }

    /**
     * 设置优惠金额
     *
     * @param discountprice 优惠金额
     */
    public void setDiscountprice(BigDecimal discountprice) {
        this.discountprice = discountprice;
    }

    /**
     * 获取菜品种类总数
     *
     * @return foodtypecount - 菜品种类总数
     */
    public Integer getFoodtypecount() {
        return foodtypecount;
    }

    /**
     * 设置菜品种类总数
     *
     * @param foodtypecount 菜品种类总数
     */
    public void setFoodtypecount(Integer foodtypecount) {
        this.foodtypecount = foodtypecount;
    }

    /**
     * 获取菜品ID/份数/单价
     *
     * @return foodinfo - 菜品ID/份数/单价
     */
    public String getFoodinfo() {
        return foodinfo;
    }

    /**
     * 设置菜品ID/份数/单价
     *
     * @param foodinfo 菜品ID/份数/单价
     */
    public void setFoodinfo(String foodinfo) {
        this.foodinfo = foodinfo == null ? null : foodinfo.trim();
    }

    /**
     * 获取订单描述
     *
     * @return descript - 订单描述
     */
    public String getDescript() {
        return descript;
    }

    /**
     * 设置订单描述
     *
     * @param descript 订单描述
     */
    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }

    /**
     * 获取预约编号
     *
     * @return reserve - 预约编号
     */
    public BigDecimal getReserve() {
        return reserve;
    }

    /**
     * 设置预约编号
     *
     * @param reserve 预约编号
     */
    public void setReserve(BigDecimal reserve) {
        this.reserve = reserve;
    }

    /**
     * 获取门店id
     *
     * @return kitchen_id - 门店id
     */
    public Long getKitchenId() {
        return kitchenId;
    }

    /**
     * 设置门店id
     *
     * @param kitchenId 门店id
     */
    public void setKitchenId(Long kitchenId) {
        this.kitchenId = kitchenId;
    }

    /**
     * 获取订单类型
1、.预定
2、排序
3、现场点餐
4、九成菜
5、聚餐

     *
     * @return type - 订单类型
1、.预定
2、排序
3、现场点餐
4、九成菜
5、聚餐

     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置订单类型
1、.预定
2、排序
3、现场点餐
4、九成菜
5、聚餐

     *
     * @param type 订单类型
1、.预定
2、排序
3、现场点餐
4、九成菜
5、聚餐

     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取餐台点赞
0.未点赞
1.已点赞
     *
     * @return center_service - 餐台点赞
0.未点赞
1.已点赞
     */
    public Integer getCenterService() {
        return centerService;
    }

    /**
     * 设置餐台点赞
0.未点赞
1.已点赞
     *
     * @param centerService 餐台点赞
0.未点赞
1.已点赞
     */
    public void setCenterService(Integer centerService) {
        this.centerService = centerService;
    }

    /**
     * 获取前厅点赞
0.未点赞
1.已点赞
     *
     * @return hall_service - 前厅点赞
0.未点赞
1.已点赞
     */
    public Integer getHallService() {
        return hallService;
    }

    /**
     * 设置前厅点赞
0.未点赞
1.已点赞
     *
     * @param hallService 前厅点赞
0.未点赞
1.已点赞
     */
    public void setHallService(Integer hallService) {
        this.hallService = hallService;
    }

    /**
     * 获取推送：
0.待推送
1.推送待确认
2.已推送
     *
     * @return is_push - 推送：
0.待推送
1.推送待确认
2.已推送
     */
    public Integer getIsPush() {
        return isPush;
    }

    /**
     * 设置推送：
0.待推送
1.推送待确认
2.已推送
     *
     * @param isPush 推送：
0.待推送
1.推送待确认
2.已推送
     */
    public void setIsPush(Integer isPush) {
        this.isPush = isPush;
    }

    /**
     * 获取订单完成时间
     *
     * @return over_time - 订单完成时间
     */
    public Date getOverTime() {
        return overTime;
    }

    /**
     * 设置订单完成时间
     *
     * @param overTime 订单完成时间
     */
    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    /**
     * 获取制作状态
1：待制作
2、已导入平板
3：制作中
4：制作完
     *
     * @return product_status - 制作状态
1：待制作
2、已导入平板
3：制作中
4：制作完
     */
    public Integer getProductStatus() {
        return productStatus;
    }

    /**
     * 设置制作状态
1：待制作
2、已导入平板
3：制作中
4：制作完
     *
     * @param productStatus 制作状态
1：待制作
2、已导入平板
3：制作中
4：制作完
     */
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * 获取是否打印:
1,已打印；
2，未打印
     *
     * @return isPrint - 是否打印:
1,已打印；
2，未打印
     */
    public Integer getIsprint() {
        return isprint;
    }

    /**
     * 设置是否打印:
1,已打印；
2，未打印
     *
     * @param isprint 是否打印:
1,已打印；
2，未打印
     */
    public void setIsprint(Integer isprint) {
        this.isprint = isprint;
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
}