package com.glsx.vasp.business.modules.storeGoods.entity;

import com.glsx.vasp.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (由平台)配置到门店的商品到店价
 * 
 * @author liumh
 * @date 2019-04-15 14:56:01
 */
@Entity
@Table(name = "d_store_goods_config")
public class StoreGoodsConfig extends BaseEntity {

	/**
	 * 门店id
	 */
	private Integer storeId;
	/**
	 * 商品id
	 */
	private Integer goodsId;
	/**
	 * 到店价
	 */
	private BigDecimal shopPrice;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
	}
}
