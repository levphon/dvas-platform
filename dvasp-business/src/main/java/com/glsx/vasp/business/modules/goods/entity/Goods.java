package com.glsx.vasp.business.modules.goods.entity;

import com.glsx.vasp.entity.BaseEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 商品表
 * 
 * @author liumh
 * @date 2019-04-01 17:52:54
 */
@Entity
@Table(name = "d_goods")
@ApiModel("商品表")
public class Goods extends BaseEntity {

	/**
	 * 类别code
	 */
	@Column(name = "category_code", length = 32)
	private String categoryCode;
	/**
	 * 商品全名称
	 */
	@Column(name = "name", length = 255)
	private String name;
	/**
	 * 商品别名
	 */
	@Column(name = "alias", length = 100)
	private String alias;
	/**
	 * 商品标签
	 */
	@Column(name = "label", length = 1024)
	private String label;
	/**
	 * 成本价
	 */
	@Column(name = "cost_price", length = 20)
	private BigDecimal costPrice;
	/**
	 * 到店价
	 */
	@Column(name = "shop_price", length = 20)
	private BigDecimal shopPrice;
	/**
	 * 零售价
	 */
	@Column(name = "retail_price", length = 20)
	private BigDecimal retailPrice;
	/**
	 * 商品主图片
	 */
	@Column(name = "pic_url", length = 255)
	private String picUrl;
	/**
	 * 商品详情
	 */
	@Column(name = "details", length = 1024)
	private String details;
	/**
	 * 所属商家（sp）
	 */
	@Column(name = "org_id", length = 11)
	private Integer orgId;

	@Column(name = "gl_self_support", length = 2)
	private Integer glSelfSupport;
	/**
	 * 备注
	 */
	@Column(name = "remark", length = 200)
	private String remark;

	@Column(name = "base_goods_id", length = 11)
	private Integer baseGoodsId;


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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
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

	public Integer getGlSelfSupport() {
		return glSelfSupport;
	}

	public void setGlSelfSupport(Integer glSelfSupport) {
		this.glSelfSupport = glSelfSupport;
	}

	public Integer getBaseGoodsId() {
		return baseGoodsId;
	}

	public void setBaseGoodsId(Integer baseGoodsId) {
		this.baseGoodsId = baseGoodsId;
	}
}
