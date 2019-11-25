package com.glsx.vasp.business.modules.goods.model;

import com.glsx.vasp.entity.BaseEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品表
 * 
 * @author liumh
 * @date 2019-04-01 17:52:54
 */
public class GoodsDetailModel extends BaseEntity {

	/**
	 * 类别code
	 */
	private String categoryCode;
	/**
	 * 商品全名称
	 */
	private String name;
	/**
	 * 商品别名
	 */
	private String alias;
	/**
	 * 商品标签
	 */
	private String label;
	/**
	 * 成本价
	 */
	private BigDecimal costPrice;
	/**
	 * 到店价
	 */
	private BigDecimal shopPrice;
	/**
	 * 零售价
	 */
	private BigDecimal retailPrice;
	/**
	 * 商品主图片
	 */
	private List<String> picUrl;
	/**
	 * 商品详情
	 */
	private String details;
	/**
	 * 所属商家（sp）
	 */
	private Integer orgId;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 启用状态 1:上架,2:下架
	 */


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public List<String> getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(List<String> picUrl) {
		this.picUrl = picUrl;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
