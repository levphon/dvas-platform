package com.glsx.vasp.admin.modules.goods.entity;

import com.glsx.vasp.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "d_category")
public class Category extends BaseEntity {

    /**
     * 类别code
     */
    private String code;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 父级id
     */
    @Column(name = "parent_code")
    private String parentCode;

    /**
     * 分类层级
     */
    private Byte level;

    /**
     * 备注
     */
    private String remark;

    private String icon;

    /**
     * 获取类别code
     *
     * @return code - 类别code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置类别code
     *
     * @param code 类别code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取类别名称
     *
     * @return name - 类别名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类别名称
     *
     * @param name 类别名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取父级id
     *
     * @return parent_code - 父级id
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 设置父级id
     *
     * @param parentCode 父级id
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * 获取分类层级
     *
     * @return level - 分类层级
     */
    public Byte getLevel() {
        return level;
    }

    /**
     * 设置分类层级
     *
     * @param level 分类层级
     */
    public void setLevel(Byte level) {
        this.level = level;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}