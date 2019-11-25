package com.glsx.vasp.admin.modules.coupon.model;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/11 18:05
 * @since 1.0.0
 */
public class CouponStoreVO {

    /**
     * 门店和优惠券关系id
     */
    private Integer scId;

    /**
     * 门店id
     */
    private Integer storeId;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 优惠劵名称
     */
    private String couponTitle;

    /**
     * 用券商品
     */
    private String goodsName;

    /**
     * 用劵数量
     */
    private Integer usedQuantity;

    /**
     * 利润
     */
    private BigDecimal profit;

    /**
     * 使用门槛
     */
    private Integer usedThreshold;

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getUsedQuantity() {
        return usedQuantity;
    }

    public void setUsedQuantity(Integer usedQuantity) {
        this.usedQuantity = usedQuantity;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Integer getUsedThreshold() {
        return usedThreshold;
    }

    public void setUsedThreshold(Integer usedThreshold) {
        this.usedThreshold = usedThreshold;
    }
}
