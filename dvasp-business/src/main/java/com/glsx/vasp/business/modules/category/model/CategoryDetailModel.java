package com.glsx.vasp.business.modules.category.model;

import com.glsx.vasp.business.modules.category.entity.Category;

import java.io.Serializable;
import java.util.List;

/**
 * 商品分类表
 * 
 * @author liumh
 * @date 2019-04-01 15:17:15
 */

public class CategoryDetailModel implements Serializable {
	/**
	 * 父类别code
	 */
	private String parentCode;

	private List<Category> categoryDetail;

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public List<Category> getCategoryDetail() {
		return categoryDetail;
	}

	public void setCategoryDetail(List<Category> categoryDetail) {
		this.categoryDetail = categoryDetail;
	}
}
