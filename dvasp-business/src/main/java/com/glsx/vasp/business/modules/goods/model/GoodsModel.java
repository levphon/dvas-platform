package com.glsx.vasp.business.modules.goods.model;

import com.glsx.vasp.business.modules.goods.entity.Goods;

import java.util.List;

/**
 * 商品表
 * 
 * @author liumh
 * @date 2019-04-01 17:52:54
 */
public class GoodsModel {

	private String categoryCode;

	private List<Goods> goodsDetail;

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public List<Goods> getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(List<Goods> goodsDetail) {
		this.goodsDetail = goodsDetail;
	}
}
