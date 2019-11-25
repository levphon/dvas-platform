package com.glsx.vasp.business.modules.coupon.entity;

import com.glsx.vasp.entity.BaseEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 由（平台）派发/（用户）领用优惠券表
 *
 * @author liumh
 * @date 2019-04-04 18:47:32
 */
@Entity
@Table(name = "d_coupon_recipients")
@ApiModel("优惠券表")
public class CouponRecipients extends BaseEntity {
    /**
     * 门店id
     */
    @Column(name = "user_id", length = 32)
    private Integer userId;
    /**
     * 优惠券id
     */
    @Column(name = "coupon_id", length = 32)
    private Integer couponId;

    @Column(name = "coupon_title", length = 50)
    private String couponTitle;
    /**
     * 商品id
     */
    @Column(name = "goods_id", length = 32)
    private Integer goodsId;
    /**
     * 领券时劵成本价
     */
    @Column(name = "cost_amount", length = 20)
    private BigDecimal costAmount;
    /**
     * 领券时劵抵用金额
     */
    @Column(name = "used_amount", length = 20)
    private BigDecimal usedAmount;
    /**
     * 领券时抵用券数量
     */
    @Column(name = "used_quantity", length = 5)
    private Integer usedQuantity;
    /**
     * 金融订单编号
     */
    @Column(name = "finance_order_no", length = 32)
    private String financeOrderNo;
    /**
     * 车架号
     */
    @Column(name = "vin_no", length = 32)
    private String vinNo;
    /**
     * 券使用状态：1未使用，2已使用
     */
    @Column(name = "used_flag", length = 2)
    private Integer usedFlag;

    @Column(name = "used_threshold", length = 2)
    private Integer usedThreshold;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public Integer getUsedFlag() {
        return usedFlag;
    }

    public void setUsedFlag(Integer usedFlag) {
        this.usedFlag = usedFlag;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public String getFinanceOrderNo() {
        return financeOrderNo;
    }

    public void setFinanceOrderNo(String financeOrderNo) {
        this.financeOrderNo = financeOrderNo;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public Integer getUsedThreshold() {
        return usedThreshold;
    }

    public void setUsedThreshold(Integer usedThreshold) {
        this.usedThreshold = usedThreshold;
    }
}
