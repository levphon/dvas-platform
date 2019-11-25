package com.glsx.vasp.business.modules.order.entity;

import com.glsx.vasp.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单商品关系表
 *
 * @author liumh
 * @date 2019-04-09 15:03:07
 */
@Entity
@Table(name = "d_store_order_goods")
public class StoreOrderGoods  {

    private static final long serialVersionUID = -4219943676486622613L;
    /**
     * 主键
     */
    @Column(name = "id")
    private Integer id;
    /**
     * 订单流水
     */
    @Column(name = "order_no", length = 32)
    private String orderNo;
    /**
     * 商品id
     */
    @Column(name = "goods_Id")
    private Integer goodsId;
    /**
     *
     */
    @Column(name = "goods_name", length = 255)
    private String goodsName;
    /**
     * 商品数量
     */
    @Column(name = "goods_num", length = 5)
    private Integer goodsNum;
    /**
     * 商品价格
     */
    @Column(name = "goods_price", length = 20)
    private BigDecimal goodsPrice;
    /**
     *
     */
    @Column(name = "cost_price", length = 20)
    private BigDecimal costPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }
}
