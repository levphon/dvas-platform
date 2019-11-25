package com.glsx.vasp.business.modules.category.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品分类表
 * 
 * @author Mark
 * @date 2019-04-01 15:17:15
 */
@Entity
@Table(name = "d_category")
@ApiModel("商品分类")
public class Category implements Serializable {

	private static final long serialVersionUID = -7522763861046483992L;
	/**
	 * 主键
	 */
	@Column(name = "id", length = 20)
	private Integer id;
	/**
	 * 类别code
	 */
	@Column(name = "code", length = 32)
	private String code;
	/**
	 * 类别名称
	 */
	@Column(name = "name", length = 64)
	private String name;
	/**
	 * 父级id
	 */
	@Column(name = "parent_code", length = 32)
	private String parentCode;
	/**
	 * 分类层级
	 */
	@Column(name = "level", length = 2)
	private Integer level;
	/**
	 * 备注
	 */
	@Column(name = "remark", length = 200)
	private String remark;
	/**
	 * 类别图片url
	 */
	@Column(name = "icon")
	private String icon;
	/**
	 * 启用状态 1:启用,2:停用
	 */
	@Column(name = "enable_status", length = 1)
	private Integer enableStatus;
	/**
	 * 删除标记  -1：已删除  0：正常
	 */
	@Column(name = "del_flag")
	private Integer delFlag;
	/**
	 * 创建人
	 */
	@Column(name = "create_user")
	private Integer createUser;
	/**
	 * 修改人
	 */
	@Column(name = "update_use")
	private Integer updateUser;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
