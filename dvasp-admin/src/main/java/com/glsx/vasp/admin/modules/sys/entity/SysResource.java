package com.glsx.vasp.admin.modules.sys.entity;

import com.glsx.vasp.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 系统资源
 */
@Entity
@Table(name = "sys_menu")
@ApiModel("系统资源")
public class SysResource extends BaseEntity {

    /**
     * 资源编号
     */
    @ApiModelProperty("资源编号")
    @Column(name = "menu_id", length = 20)
    private Integer menuId;

    /**
     * 资源名称
     */
    @ApiModelProperty("资源名称")
    @Column(name = "name", length = 64)
    private String menuName;

    /**
     * 上级资源
     */
    @ApiModelProperty("上级资源")
    @Column(name = "parent_id", length = 20)
    private Integer parentId;

    @Transient
    private String parentName;

    /**
     * 类型 0：目录   1：菜单   2：按钮
     */
    @ApiModelProperty(value = "资源类型", notes = "类型 M：目录   C：菜单   F：按钮   VC: 伪菜单")
    @Column(name = "type", length = 10)
    private String type;

    /**
     * 资源路径
     */
    @ApiModelProperty("资源路径")
    @Column(name = "url", length = 200)
    private String url;

    /**
     * 模块组件
     */
    @ApiModelProperty("模块组件")
    @Column(name = "component", length = 32)
    private String component;

    /**
     * 资源授权 前后端分类暂时用不到
     */
    @ApiModelProperty("授权")
    @Column(name = "perms", length = 32)
    private String perms;

    /**
     * 资源图标
     */
    @ApiModelProperty("资源图标")
    @Column(name = "icon", length = 200)
    private String icon;

    /**
     * 排序
     */
    @Column(name = "order_num", length = 10)
    private Integer orderNum;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
