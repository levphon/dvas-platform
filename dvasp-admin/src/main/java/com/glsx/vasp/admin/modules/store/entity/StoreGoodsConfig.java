package com.glsx.vasp.admin.modules.store.entity;

import com.glsx.vasp.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "d_store_goods_config")
public class StoreGoodsConfig extends BaseEntity {

    /**
     * 门店id
     */
    @Column(name = "store_id")
    private Integer storeId;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 到店价
     */
    @Column(name = "shop_price")
    private BigDecimal shopPrice;

    /**
     * 获取门店id
     *
     * @return store_id - 门店id
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * 设置门店id
     *
     * @param storeId 门店id
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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
     * 获取到店价
     *
     * @return shop_price - 到店价
     */
    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    /**
     * 设置到店价
     *
     * @param shopPrice 到店价
     */
    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

}