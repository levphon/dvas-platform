package com.glsx.vasp.admin.modules.coupon.entity;

import com.glsx.vasp.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "d_coupon_recipients")
public class CouponRecipients extends BaseEntity {

    /**
     * 优惠券id
     */
    @Column(name = "coupon_id")
    private Integer couponId;

    /**
     * 优惠券名称
     */
    @Column(name = "coupon_title")
    private String couponTitle;

    /**
     * 门店用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 劵成本价
     */
    @Column(name = "cost_amount")
    private BigDecimal costAmount;

    /**
     * 劵抵用金额
     */
    @Column(name = "used_amount")
    private BigDecimal usedAmount;

    /**
     * 抵用券数量
     */
    @Column(name = "used_quantity")
    private Integer usedQuantity;

    /**
     * 使用状态：1使用，2未使用
     */
    @Column(name = "used_flag")
    private Byte usedFlag;

    /**
     * 金融订单编号
     */
    @Column(name = "finance_order_no")
    private String financeOrderNo;

    /**
     * 车架号
     */
    @Column(name = "vin_no")
    private String vinNo;

    /**
     * 获取优惠券id
     *
     * @return coupon_id - 优惠券id
     */
    public Integer getCouponId() {
        return couponId;
    }

    /**
     * 设置优惠券id
     *
     * @param couponId 优惠券id
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取劵成本价
     *
     * @return cost_amount - 劵成本价
     */
    public BigDecimal getCostAmount() {
        return costAmount;
    }

    /**
     * 设置劵成本价
     *
     * @param costAmount 劵成本价
     */
    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    /**
     * 获取劵抵用金额
     *
     * @return used_amount - 劵抵用金额
     */
    public BigDecimal getUsedAmount() {
        return usedAmount;
    }

    /**
     * 设置劵抵用金额
     *
     * @param usedAmount 劵抵用金额
     */
    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
    }

    /**
     * 获取抵用券数量
     *
     * @return used_quantity - 抵用券数量
     */
    public Integer getUsedQuantity() {
        return usedQuantity;
    }

    /**
     * 设置抵用券数量
     *
     * @param usedQuantity 抵用券数量
     */
    public void setUsedQuantity(Integer usedQuantity) {
        this.usedQuantity = usedQuantity;
    }

    /**
     * 获取使用状态：1使用，2未使用
     *
     * @return used_flag - 券使用状态：1未使用，2已使用
     */
    public Byte getUsedFlag() {
        return usedFlag;
    }

    /**
     * 设置使用状态：1使用，2未使用
     *
     * @param usedFlag 券使用状态：1未使用，2已使用
     */
    public void setUsedFlag(Byte usedFlag) {
        this.usedFlag = usedFlag;
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
    
}