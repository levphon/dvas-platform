package com.glsx.vasp.business.modules.shopcar.entity;

import com.glsx.vasp.entity.BaseEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 购物车表
 * 
 * @author liumh
 * @date 2019-04-02 16:00:37
 */
@Entity
@Table(name = "d_shopcar")
@ApiModel("购物车")
public class Shopcar extends BaseEntity {
	/**
	 * 用户id
	 */
	@Column(name = "user_id", length = 20)
	private Integer userId;
	/**
	 * 商品id
	 */
	@Column(name = "goods_id", length = 11)
	private Integer goodsId;
	/**
	 * 商品数量
	 */
	@Column(name = "goods_num", length = 4)
	private Integer goodsNum;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
}
