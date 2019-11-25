package com.glsx.vasp.admin.modules.order.entity;

import com.glsx.vasp.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

@Table(name = "d_store_order")
public class StoreOrder extends BaseEntity {

    /**
     * 门店用户id
     */
    @Column(name = "store_user_id")
    private Integer userId;

    /**
     * 门店名称
     */
    @Transient
    private String storeName;

    /**
     * 订单流水
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 订单支付金额
     */
    @Column(name = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 订单优惠金额
     */
    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    /**
     * 订单利润
     */
    private BigDecimal profit;

//    /**
//     * 金融订单编号
//     */
//    @Column(name = "finance_order_no")
//    private String financeOrderNo;
//
//    /**
//     * 车架号
//     */
//    @Column(name = "vin_no")
//    private String vinNo;

    /**
     * 订单状态：1已下单，2已安装
     */
    private Integer status;

    /**
     * 订单商品详情
     */
    @Transient
    private List<StoreOrderGoods> goodsList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取订单优惠金额
     *
     * @return discount_amount - 订单优惠金额
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * 设置订单优惠金额
     *
     * @param discountAmount 订单优惠金额
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * 获取订单利润
     *
     * @return profit - 订单利润
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * 设置订单利润
     *
     * @param profit 订单利润
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

//    /**
//     * 获取金融订单编号
//     *
//     * @return finance_order_no - 金融订单编号
//     */
//    public String getFinanceOrderNo() {
//        return financeOrderNo;
//    }
//
//    /**
//     * 设置金融订单编号
//     *
//     * @param financeOrderNo 金融订单编号
//     */
//    public void setFinanceOrderNo(String financeOrderNo) {
//        this.financeOrderNo = financeOrderNo == null ? null : financeOrderNo.trim();
//    }
//
//    /**
//     * 获取车架号
//     *
//     * @return vin_no - 车架号
//     */
//    public String getVinNo() {
//        return vinNo;
//    }
//
//    /**
//     * 设置车架号
//     *
//     * @param vinNo 车架号
//     */
//    public void setVinNo(String vinNo) {
//        this.vinNo = vinNo == null ? null : vinNo.trim();
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<StoreOrderGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<StoreOrderGoods> goodsList) {
        this.goodsList = goodsList;
    }

}