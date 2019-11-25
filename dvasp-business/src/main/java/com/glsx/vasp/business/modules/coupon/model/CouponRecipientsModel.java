package com.glsx.vasp.business.modules.coupon.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 由（平台）派发/（用户）领用优惠券表
 *
 * @author liumh
 * @date 2019-04-04 18:47:32
 */
public class CouponRecipientsModel implements Serializable {
    private static final long serialVersionUID = -1152556346432529288L;

    /**
     * 优惠券id
     */
    private Integer couponId;

    private String couponTitle;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 领券时劵成本价
     */
    private BigDecimal costAmount;
    /**
     * 领券时劵抵用金额
     */
    private BigDecimal usedAmount;
    /**
     * 领券时抵用券数量
     */
    private Integer usedQuantity;

    /**
     * 使用门槛：1无使用门槛，2需做金融
     */
    private Integer usedThreshold;

    private String name;

    private BigDecimal payAmount;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public BigDecimal getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
    }

    public Integer getUsedQuantity() {
        return usedQuantity;
    }

    public void setUsedQuantity(Integer usedQuantity) {
        this.usedQuantity = usedQuantity;
    }


    public Integer getUsedThreshold() {
        return usedThreshold;
    }

    public void setUsedThreshold(Integer usedThreshold) {
        this.usedThreshold = usedThreshold;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
