package com.glsx.vasp.admin.modules.sys.entity;

import com.glsx.vasp.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * 角色表
 */
@Entity
@Table(name = "sys_role")
@ApiModel
public class SysRole extends BaseEntity {

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    @Column(name = "name", length = 64)
    private String name;

    /**
     * 角色说明
     */
    @ApiModelProperty("角色说明")
    @Column(name = "comment", length = 256)
    private String comment;

    private List<Integer> menuIdList;

    // setGet name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // setGet comment
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }

    public List<Integer> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }

}
