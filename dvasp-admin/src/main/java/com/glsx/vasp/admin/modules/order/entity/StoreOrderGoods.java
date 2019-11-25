package com.glsx.vasp.admin.modules.order.entity;

import com.glsx.vasp.admin.modules.coupon.model.CouponRecipientsModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "d_store_order_goods")
public class StoreOrderGoods implements Serializable {

    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 订单流水
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品数量
     */
    @Column(name = "goods_num")
    private Integer goodsNum;

    /**
     * 商品成本价
     */
    @Column(name = "cost_price")
    private BigDecimal costPrice;

    /**
     * 商品下单价格（登录后的到店价，没登录时候的零售价，但不存在不登录可以下单的情况）
     */
    @Column(name = "goods_price")
    private BigDecimal goodsPrice;

    /**
     * 商品零售价
     */
    private BigDecimal retailPrice;

    /**
     * 商品总到店价
     */
    private BigDecimal totalShopPrice;

    /**
     * 商品总成本
     */
    private BigDecimal totalCostPrice;

    /**
     * 券支付金额=商品数量*券抵用数量*券成本
     */
    @Transient
    private BigDecimal payCouponAmount;

    /**
     * 用券后商品支付金额，没用券=goodsPrice*goodsNum
     */
    @Transient
    private BigDecimal payGoodsAmount;

    /**
     * 商品利润
     */
    @Transient
    private BigDecimal profit;

    /**
     * 用券数量
     */
    @Transient
    private Integer usedCouponQuantity;

    /**
     * 商品用券详情
     */
    @Transient
    private CouponRecipientsModel coupons;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单流水
     *
     * @return order_no - 订单流水
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单流水
     *
     * @param orderNo 订单流水
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品数量
     *
     * @return goods_num - 商品数量
     */
    public Integer getGoodsNum() {
        return goodsNum;
    }

    /**
     * 设置商品数量
     *
     * @param goodsNum 商品数量
     */
    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 获取商品价格
     *
     * @return goods_price - 商品价格
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置商品价格
     *
     * @param goodsPrice 商品价格
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getTotalShopPrice() {
        return totalShopPrice;
    }

    public void setTotalShopPrice(BigDecimal totalShopPrice) {
        this.totalShopPrice = totalShopPrice;
    }

    public BigDecimal getTotalCostPrice() {
        return totalCostPrice;
    }

    public void setTotalCostPrice(BigDecimal totalCostPrice) {
        this.totalCostPrice = totalCostPrice;
    }

    public BigDecimal getPayCouponAmount() {
        return payCouponAmount;
    }

    public void setPayCouponAmount(BigDecimal payCouponAmount) {
        this.payCouponAmount = payCouponAmount;
    }

    public BigDecimal getPayGoodsAmount() {
        return payGoodsAmount;
    }

    public void setPayGoodsAmount(BigDecimal payGoodsAmount) {
        this.payGoodsAmount = payGoodsAmount;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Integer getUsedCouponQuantity() {
        return usedCouponQuantity;
    }

    public void setUsedCouponQuantity(Integer usedCouponQuantity) {
        this.usedCouponQuantity = usedCouponQuantity;
    }

    public CouponRecipientsModel getCoupons() {
        return coupons;
    }

    public void setCoupons(CouponRecipientsModel coupons) {
        this.coupons = coupons;
    }

}