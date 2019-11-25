package com.glsx.vasp.business.modules.order.model;

import com.glsx.vasp.business.modules.order.entity.StoreOrder;
import com.glsx.vasp.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 门店订单表
 * 
 * @author liumh
 * @date 2019-04-09 14:21:28
 */
public class StoreOrderModel extends StoreOrder {

	private  String picUrl;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
