package com.glsx.vasp.admin.modules.coupon.model;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/15 21:08
 * @since 1.0.0
 */
public class CouponGoodsVO {

    private Integer couponId;
    private String couponTitle;
    private BigDecimal couponCostAmount;
    private BigDecimal couponUsedAmount;
    private Integer couponQuota;
    private Integer usedThreshold;

    private Integer goodsId;
    private String goodsName;
    private BigDecimal goodsCostPrice;
    private BigDecimal goodsShopPrice;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public BigDecimal getCouponCostAmount() {
        return couponCostAmount;
    }

    public void setCouponCostAmount(BigDecimal couponCostAmount) {
        this.couponCostAmount = couponCostAmount;
    }

    public BigDecimal getCouponUsedAmount() {
        return couponUsedAmount;
    }

    public void setCouponUsedAmount(BigDecimal couponUsedAmount) {
        this.couponUsedAmount = couponUsedAmount;
    }

    public Integer getCouponQuota() {
        return couponQuota;
    }

    public void setCouponQuota(Integer couponQuota) {
        this.couponQuota = couponQuota;
    }

    public Integer getUsedThreshold() {
        return usedThreshold;
    }

    public void setUsedThreshold(Integer usedThreshold) {
        this.usedThreshold = usedThreshold;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsCostPrice() {
        return goodsCostPrice;
    }

    public void setGoodsCostPrice(BigDecimal goodsCostPrice) {
        this.goodsCostPrice = goodsCostPrice;
    }

    public BigDecimal getGoodsShopPrice() {
        return goodsShopPrice;
    }

    public void setGoodsShopPrice(BigDecimal goodsShopPrice) {
        this.goodsShopPrice = goodsShopPrice;
    }

}
