package com.glsx.vasp.admin.modules.order.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 3/27/2019 13:54
 * @since 1.0.0
 */
public class StoreOrderExportModel extends BaseRowModel {

//   需求文档V1.0.0导出：订单编号、门店名称、订购商品、到店价、用劵名称、用劵数量、精品利润、抵用后支付金额、劵支付金额、订单状态、下单时间

    @ExcelProperty(value = "订单编号", index = 0)
    private String orderNo;

    @ExcelProperty(value = "门店名称", index = 1)
    private String storeName;

    @ExcelProperty(value = "订购商品", index = 2)
    private String goodsName;

    @ExcelProperty(value = "到店价", index = 3)
    private BigDecimal goodsShopPrice;

    @ExcelProperty(value = "商品数量", index = 4)
    private Integer goodsNum;

    @ExcelProperty(value = "用劵名称", index = 5)
    private String couponTitle;

    @ExcelProperty(value = "用券数量", index = 6)
    private Integer couponUsedQuantity;

    @ExcelProperty(value = "精品利润", index = 7)
    private BigDecimal delicateProfit;

    @ExcelProperty(value = "抵用后支付金额", index = 8)
    private BigDecimal goodsPayAmount;

    @ExcelProperty(value = "券支付金额", index = 9)
    private BigDecimal couponPayAmount;

    @ExcelProperty(value = "订单状态", index = 10)
    private String orderStatus;

    @ExcelProperty(value = "下单时间", index = 11)
    private String createTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsShopPrice() {
        return goodsShopPrice;
    }

    public void setGoodsShopPrice(BigDecimal goodsShopPrice) {
        this.goodsShopPrice = goodsShopPrice;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public Integer getCouponUsedQuantity() {
        return couponUsedQuantity;
    }

    public void setCouponUsedQuantity(Integer couponUsedQuantity) {
        this.couponUsedQuantity = couponUsedQuantity;
    }

    public BigDecimal getDelicateProfit() {
        return delicateProfit;
    }

    public void setDelicateProfit(BigDecimal delicateProfit) {
        this.delicateProfit = delicateProfit;
    }

    public BigDecimal getGoodsPayAmount() {
        return goodsPayAmount;
    }

    public void setGoodsPayAmount(BigDecimal goodsPayAmount) {
        this.goodsPayAmount = goodsPayAmount;
    }

    public BigDecimal getCouponPayAmount() {
        return couponPayAmount;
    }

    public void setCouponPayAmount(BigDecimal couponPayAmount) {
        this.couponPayAmount = couponPayAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
