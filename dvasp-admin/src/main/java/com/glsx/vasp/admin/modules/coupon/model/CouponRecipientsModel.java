package com.glsx.vasp.admin.modules.coupon.model;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/9 11:15
 * @since 1.0.0
 */
public class CouponRecipientsModel {

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用券商品id
     */
    private Integer goodsId;

    /**
     * 券id
     */
    private String couponId;

    /**
     * 券名称
     */
    private String couponTitle;

    /**
     * 劵成本价
     */
    private BigDecimal costAmount;

    /**
     * 劵抵用金额
     */
    private BigDecimal usedAmount;

    /**
     * 抵用券数量
     */
    private Integer usedQuantity;

    /**
     * 金融订单编号
     */
    private String financeOrderNo;

    /**
     * 车架号
     */
    private String vinNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
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
