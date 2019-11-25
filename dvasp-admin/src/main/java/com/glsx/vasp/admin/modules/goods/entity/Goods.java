package com.glsx.vasp.admin.modules.goods.entity;

import com.glsx.vasp.entity.BaseEntity;
import com.glsx.vasp.validator.group.AddGroup;
import com.glsx.vasp.validator.group.UpdateGroup;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Table(name = "d_goods")
public class Goods extends BaseEntity {

    /**
     * 类别code
     */
    @NotNull(message = "商品分类不能为空", groups = {AddGroup.class})
    @Column(name = "category_code")
    private Integer categoryCode;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空", groups = {AddGroup.class})
    private String name;

    /**
     * 商品别名
     */
    private String alias;

    /**
     * 商品标签
     */
    private String label;

    /**
     * 成本价
     */
    @NotNull(message = "成本价不能为空", groups = {AddGroup.class})
    @Column(name = "cost_price")
    private BigDecimal costPrice;

    /**
     * 到店价
     */
    @NotNull(message = "到店价不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Column(name = "shop_price")
    private BigDecimal shopPrice;

    /**
     * 零售价
     */
    @NotNull(message = "零售价不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Column(name = "retail_price")
    private BigDecimal retailPrice;

    /**
     * 商品主图片
     */
    @NotBlank(message = "商品图片不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 商品详情
     */
    @NotBlank(message = "商品说明不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String details;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属商家（sp）
     */
    @Column(name = "org_id")
    private Integer orgId;

    @Transient
    private List<GoodsPicture> goodsPictures;

    /**
     * 上架状态 1:上架,2:下架
     */
//    @Column(name = "enable_status")
//    private Byte enableStatus;

    /**
     * 获取类别code
     *
     * @return category_code - 类别code
     */
    public Integer getCategoryCode() {
        return categoryCode;
    }

    /**
     * 设置类别code
     *
     * @param categoryCode 类别code
     */
    public void setCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * 获取商品全名称
     *
     * @return name - 商品全名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品全名称
     *
     * @param name 商品全名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取商品别名
     *
     * @return alias - 商品别名
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 设置商品别名
     *
     * @param alias 商品别名
     */
    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    /**
     * 获取商品标签
     *
     * @return label - 商品标签
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置商品标签
     *
     * @param label 商品标签
     */
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    /**
     * 获取成本价
     *
     * @return cost_price - 成本价
     */
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    /**
     * 设置成本价
     *
     * @param costPrice 成本价
     */
    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * 获取到店价
     *
     * @return shop_price - 到店价
     */
    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    /**
     * 设置到店价
     *
     * @param shopPrice 到店价
     */
    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    /**
     * 获取零售价
     *
     * @return retail_price - 零售价
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * 设置零售价
     *
     * @param retailPrice 零售价
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取商品详情
     *
     * @return details - 商品详情
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置商品详情
     *
     * @param details 商品详情
     */
    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
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
     * 获取所属商家（sp）
     *
     * @return org_id - 所属商家（sp）
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 设置所属商家（sp）
     *
     * @param orgId 所属商家（sp）
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public List<GoodsPicture> getGoodsPictures() {
        return goodsPictures;
    }

    public void setGoodsPictures(List<GoodsPicture> goodsPictures) {
        this.goodsPictures = goodsPictures;
    }

}