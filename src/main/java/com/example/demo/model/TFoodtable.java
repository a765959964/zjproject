package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_foodtable")
public class TFoodtable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 菜品ID
     */
    private Long foodid;

    /**
     * 综合
     */
    private Long global;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 评分次数
     */
    private Integer scorecount;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 菜品图标地址
     */
    private String iconaddress;

    /**
     * 菜图片地址
     */
    private String imageaddress;

    /**
     * 菜名
     */
    private String foodname;

    /**
     * 酸度
     */
    private Integer sour;

    /**
     * 甜度
     */
    private Integer sweet;

    /**
     * 咸度
     */
    private Integer salty;

    /**
     * 辣度
     */
    private Integer hot;

    /**
     * 作者名
     */
    private String authorname;

    /**
     * 作者简介
     */
    private String authorbrief;

    /**
     * 作者图标
     */
    private String authoricon;

    /**
     * 作者详情
     */
    private String authordetail;

    /**
     * 菜品简介
     */
    private String foodbrief;

    /**
     * 菜品详情
     */
    private String fooddetail;

    /**
     * 配菜菜品分类号
     */
    private String matchfoodnum;

    /**
     * 搭配菜品ID
     */
    private String matchfoodid;

    /**
     * 菜品状态
     */
    private Integer foodstat;

    /**
     * 菜品详情路径
     */
    private String jsonpath;

    /**
     * foodtype_id
     */
    @Column(name = "foodtype_id")
    private Integer foodtypeId;

    /**
     * 0 否 1 是
     */
    private Integer isdel;

    /**
     * 菜系ID
     */
    @Column(name = "food_common_id")
    private String foodCommonId;

    /**
     * 食材ID
     */
    @Column(name = "material_id")
    private String materialId;

    /**
     * 味型id
     */
    @Column(name = "taste_id")
    private String tasteId;

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

    @Column(name = "create_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 制作时间
     */
    private Integer wastetime;

    /**
     * 版本号
     */
    private String version;


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
     * 获取菜品ID
     *
     * @return foodid - 菜品ID
     */
    public Long getFoodid() {
        return foodid;
    }

    /**
     * 设置菜品ID
     *
     * @param foodid 菜品ID
     */
    public void setFoodid(Long foodid) {
        this.foodid = foodid;
    }

    /**
     * 获取综合
     *
     * @return global - 综合
     */
    public Long getGlobal() {
        return global;
    }

    /**
     * 设置综合
     *
     * @param global 综合
     */
    public void setGlobal(Long global) {
        this.global = global;
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
     * @return scorecount - 评分次数
     */
    public Integer getScorecount() {
        return scorecount;
    }

    /**
     * 设置评分次数
     *
     * @param scorecount 评分次数
     */
    public void setScorecount(Integer scorecount) {
        this.scorecount = scorecount;
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
     * 获取价格
     *
     * @return price - 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取菜品图标地址
     *
     * @return iconaddress - 菜品图标地址
     */
    public String getIconaddress() {
        return iconaddress;
    }

    /**
     * 设置菜品图标地址
     *
     * @param iconaddress 菜品图标地址
     */
    public void setIconaddress(String iconaddress) {
        this.iconaddress = iconaddress == null ? null : iconaddress.trim();
    }

    /**
     * 获取菜图片地址
     *
     * @return imageaddress - 菜图片地址
     */
    public String getImageaddress() {
        return imageaddress;
    }

    /**
     * 设置菜图片地址
     *
     * @param imageaddress 菜图片地址
     */
    public void setImageaddress(String imageaddress) {
        this.imageaddress = imageaddress == null ? null : imageaddress.trim();
    }

    /**
     * 获取菜名
     *
     * @return foodname - 菜名
     */
    public String getFoodname() {
        return foodname;
    }

    /**
     * 设置菜名
     *
     * @param foodname 菜名
     */
    public void setFoodname(String foodname) {
        this.foodname = foodname == null ? null : foodname.trim();
    }

    /**
     * 获取酸度
     *
     * @return sour - 酸度
     */
    public Integer getSour() {
        return sour;
    }

    /**
     * 设置酸度
     *
     * @param sour 酸度
     */
    public void setSour(Integer sour) {
        this.sour = sour;
    }

    /**
     * 获取甜度
     *
     * @return sweet - 甜度
     */
    public Integer getSweet() {
        return sweet;
    }

    /**
     * 设置甜度
     *
     * @param sweet 甜度
     */
    public void setSweet(Integer sweet) {
        this.sweet = sweet;
    }

    /**
     * 获取咸度
     *
     * @return salty - 咸度
     */
    public Integer getSalty() {
        return salty;
    }

    /**
     * 设置咸度
     *
     * @param salty 咸度
     */
    public void setSalty(Integer salty) {
        this.salty = salty;
    }

    /**
     * 获取辣度
     *
     * @return hot - 辣度
     */
    public Integer getHot() {
        return hot;
    }

    /**
     * 设置辣度
     *
     * @param hot 辣度
     */
    public void setHot(Integer hot) {
        this.hot = hot;
    }

    /**
     * 获取作者名
     *
     * @return authorname - 作者名
     */
    public String getAuthorname() {
        return authorname;
    }

    /**
     * 设置作者名
     *
     * @param authorname 作者名
     */
    public void setAuthorname(String authorname) {
        this.authorname = authorname == null ? null : authorname.trim();
    }

    /**
     * 获取作者简介
     *
     * @return authorbrief - 作者简介
     */
    public String getAuthorbrief() {
        return authorbrief;
    }

    /**
     * 设置作者简介
     *
     * @param authorbrief 作者简介
     */
    public void setAuthorbrief(String authorbrief) {
        this.authorbrief = authorbrief == null ? null : authorbrief.trim();
    }

    /**
     * 获取作者图标
     *
     * @return authoricon - 作者图标
     */
    public String getAuthoricon() {
        return authoricon;
    }

    /**
     * 设置作者图标
     *
     * @param authoricon 作者图标
     */
    public void setAuthoricon(String authoricon) {
        this.authoricon = authoricon == null ? null : authoricon.trim();
    }

    /**
     * 获取作者详情
     *
     * @return authordetail - 作者详情
     */
    public String getAuthordetail() {
        return authordetail;
    }

    /**
     * 设置作者详情
     *
     * @param authordetail 作者详情
     */
    public void setAuthordetail(String authordetail) {
        this.authordetail = authordetail == null ? null : authordetail.trim();
    }

    /**
     * 获取菜品简介
     *
     * @return foodbrief - 菜品简介
     */
    public String getFoodbrief() {
        return foodbrief;
    }

    /**
     * 设置菜品简介
     *
     * @param foodbrief 菜品简介
     */
    public void setFoodbrief(String foodbrief) {
        this.foodbrief = foodbrief == null ? null : foodbrief.trim();
    }

    /**
     * 获取菜品详情
     *
     * @return fooddetail - 菜品详情
     */
    public String getFooddetail() {
        return fooddetail;
    }

    /**
     * 设置菜品详情
     *
     * @param fooddetail 菜品详情
     */
    public void setFooddetail(String fooddetail) {
        this.fooddetail = fooddetail == null ? null : fooddetail.trim();
    }

    /**
     * 获取配菜菜品分类号
     *
     * @return matchfoodnum - 配菜菜品分类号
     */
    public String getMatchfoodnum() {
        return matchfoodnum;
    }

    /**
     * 设置配菜菜品分类号
     *
     * @param matchfoodnum 配菜菜品分类号
     */
    public void setMatchfoodnum(String matchfoodnum) {
        this.matchfoodnum = matchfoodnum == null ? null : matchfoodnum.trim();
    }

    /**
     * 获取搭配菜品ID
     *
     * @return matchfoodid - 搭配菜品ID
     */
    public String getMatchfoodid() {
        return matchfoodid;
    }

    /**
     * 设置搭配菜品ID
     *
     * @param matchfoodid 搭配菜品ID
     */
    public void setMatchfoodid(String matchfoodid) {
        this.matchfoodid = matchfoodid == null ? null : matchfoodid.trim();
    }

    /**
     * 获取菜品状态
     *
     * @return foodstat - 菜品状态
     */
    public Integer getFoodstat() {
        return foodstat;
    }

    /**
     * 设置菜品状态
     *
     * @param foodstat 菜品状态
     */
    public void setFoodstat(Integer foodstat) {
        this.foodstat = foodstat;
    }

    /**
     * 获取菜品详情路径
     *
     * @return jsonpath - 菜品详情路径
     */
    public String getJsonpath() {
        return jsonpath;
    }

    /**
     * 设置菜品详情路径
     *
     * @param jsonpath 菜品详情路径
     */
    public void setJsonpath(String jsonpath) {
        this.jsonpath = jsonpath == null ? null : jsonpath.trim();
    }

    /**
     * 获取foodtype_id
     *
     * @return foodtype_id - foodtype_id
     */
    public Integer getFoodtypeId() {
        return foodtypeId;
    }

    /**
     * 设置foodtype_id
     *
     * @param foodtypeId foodtype_id
     */
    public void setFoodtypeId(Integer foodtypeId) {
        this.foodtypeId = foodtypeId;
    }

    /**
     * 获取0 否 1 是
     *
     * @return isdel - 0 否 1 是
     */
    public Integer getIsdel() {
        return isdel;
    }

    /**
     * 设置0 否 1 是
     *
     * @param isdel 0 否 1 是
     */
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    /**
     * 获取菜系ID
     *
     * @return food_common_id - 菜系ID
     */
    public String getFoodCommonId() {
        return foodCommonId;
    }

    /**
     * 设置菜系ID
     *
     * @param foodCommonId 菜系ID
     */
    public void setFoodCommonId(String foodCommonId) {
        this.foodCommonId = foodCommonId == null ? null : foodCommonId.trim();
    }

    /**
     * 获取食材ID
     *
     * @return material_id - 食材ID
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * 设置食材ID
     *
     * @param materialId 食材ID
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    /**
     * 获取味型id
     *
     * @return taste_id - 味型id
     */
    public String getTasteId() {
        return tasteId;
    }

    /**
     * 设置味型id
     *
     * @param tasteId 味型id
     */
    public void setTasteId(String tasteId) {
        this.tasteId = tasteId == null ? null : tasteId.trim();
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
     * 获取制作时间
     *
     * @return wastetime - 制作时间
     */
    public Integer getWastetime() {
        return wastetime;
    }

    /**
     * 设置制作时间
     *
     * @param wastetime 制作时间
     */
    public void setWastetime(Integer wastetime) {
        this.wastetime = wastetime;
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