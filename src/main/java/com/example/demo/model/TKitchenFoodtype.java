package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_kitchen_foodtype")
public class TKitchenFoodtype {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 门店id
     */
    @Column(name = "kitchen_id")
    private Long kitchenId;

    /**
     * 菜品分类id
     */
    @Column(name = "foodtype_id")
    private Integer foodtypeId;

    /**
     * 菜品id
     */
    @Column(name = "food_id")
    private Long foodId;

    /**
     * 菜品名称
     */
    @Column(name = "food_name")
    private String foodName;

    /**
     * 菜系字段
     */
    @Column(name = "foodtype_field")
    private String foodtypeField;

    /**
     * 菜系id
     */
    @Column(name = "foodcommon_id")
    private Integer foodcommonId;

    /**
     * 菜品综合
     */
    @Column(name = "food_sort")
    private Integer foodSort;

    /**
     * 菜品价格
     */
    @Column(name = "kichen_price")
    private BigDecimal kichenPrice;

    /**
     * 菜品路径
     */
    private String jsonpath;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 状态
1、上线 
2、下线
3、试吃 
4、收藏 
5 删除
     */
    private Integer status;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 评分次数
     */
    @Column(name = "score_count")
    private Integer scoreCount;

    /**
     * 版本号
     */
    private String version;


    /**
     * 1 堂食 2外卖 3堂食 +外卖
     */
    @Column(name="food_type")
    private String foodType;

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
     * 获取菜品分类id
     *
     * @return foodtype_id - 菜品分类id
     */
    public Integer getFoodtypeId() {
        return foodtypeId;
    }

    /**
     * 设置菜品分类id
     *
     * @param foodtypeId 菜品分类id
     */
    public void setFoodtypeId(Integer foodtypeId) {
        this.foodtypeId = foodtypeId;
    }

    /**
     * 获取菜品id
     *
     * @return food_id - 菜品id
     */
    public Long getFoodId() {
        return foodId;
    }

    /**
     * 设置菜品id
     *
     * @param foodId 菜品id
     */
    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    /**
     * 获取菜品名称
     *
     * @return food_name - 菜品名称
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * 设置菜品名称
     *
     * @param foodName 菜品名称
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName == null ? null : foodName.trim();
    }

    /**
     * 获取菜系字段
     *
     * @return foodtype_field - 菜系字段
     */
    public String getFoodtypeField() {
        return foodtypeField;
    }

    /**
     * 设置菜系字段
     *
     * @param foodtypeField 菜系字段
     */
    public void setFoodtypeField(String foodtypeField) {
        this.foodtypeField = foodtypeField == null ? null : foodtypeField.trim();
    }

    /**
     * 获取菜系id
     *
     * @return foodcommon_id - 菜系id
     */
    public Integer getFoodcommonId() {
        return foodcommonId;
    }

    /**
     * 设置菜系id
     *
     * @param foodcommonId 菜系id
     */
    public void setFoodcommonId(Integer foodcommonId) {
        this.foodcommonId = foodcommonId;
    }

    /**
     * 获取菜品综合
     *
     * @return food_sort - 菜品综合
     */
    public Integer getFoodSort() {
        return foodSort;
    }

    /**
     * 设置菜品综合
     *
     * @param foodSort 菜品综合
     */
    public void setFoodSort(Integer foodSort) {
        this.foodSort = foodSort;
    }

    /**
     * 获取菜品价格
     *
     * @return kichen_price - 菜品价格
     */
    public BigDecimal getKichenPrice() {
        return kichenPrice;
    }

    /**
     * 设置菜品价格
     *
     * @param kichenPrice 菜品价格
     */
    public void setKichenPrice(BigDecimal kichenPrice) {
        this.kichenPrice = kichenPrice;
    }

    /**
     * 获取菜品路径
     *
     * @return jsonpath - 菜品路径
     */
    public String getJsonpath() {
        return jsonpath;
    }

    /**
     * 设置菜品路径
     *
     * @param jsonpath 菜品路径
     */
    public void setJsonpath(String jsonpath) {
        this.jsonpath = jsonpath == null ? null : jsonpath.trim();
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
     * 获取状态
1、上线 
2、下线
3、试吃 
4、收藏 
5 删除
     *
     * @return status - 状态
1、上线 
2、下线
3、试吃 
4、收藏 
5 删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
1、上线 
2、下线
3、试吃 
4、收藏 
5 删除
     *
     * @param status 状态
1、上线 
2、下线
3、试吃 
4、收藏 
5 删除
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取评分
     *
     * @return score - 评分
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * 设置评分
     *
     * @param score 评分
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * 获取评分次数
     *
     * @return score_count - 评分次数
     */
    public Integer getScoreCount() {
        return scoreCount;
    }

    /**
     * 设置评分次数
     *
     * @param scoreCount 评分次数
     */
    public void setScoreCount(Integer scoreCount) {
        this.scoreCount = scoreCount;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
}