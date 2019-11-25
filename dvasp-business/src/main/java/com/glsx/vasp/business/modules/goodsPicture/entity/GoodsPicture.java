package com.glsx.vasp.business.modules.goodsPicture.entity;


import com.glsx.vasp.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品图片表
 * 
 * @author liumh
 * @date 2019-04-02 10:04:45
 */
public class GoodsPicture extends BaseEntity {

	/**
	 * 商品id
	 */
	private Integer goodsId;
	/**
	 * 图片url
	 */
	private String picUrl;
	/**
	 * 顺序号
	 */
	private Integer picSeq;
	/**
	 * 是否主图
	 */
	private Integer isMain;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getPicSeq() {
		return picSeq;
	}

	public void setPicSeq(Integer picSeq) {
		this.picSeq = picSeq;
	}

	public Integer getIsMain() {
		return isMain;
	}

	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}
}
