package com.glsx.vasp.admin.modules.sys.model;

import com.glsx.vasp.admin.modules.sys.entity.SysOrganization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理新增组织机构请求model
 */
@ApiModel("组织机构模型")
public class SysOrganizationModel extends SysOrganization implements Comparable<SysOrganizationModel> {
    @ApiModelProperty("区域代码")
    private String areaCode;
    @ApiModelProperty("详细地址")
    private String detailedAddress;
    @ApiModelProperty("父级组织机构名称")
    private String parentOrgName;

    public SysOrganizationModel() {
    }

    public SysOrganizationModel(SysOrganization sysOrganization) {
        if (sysOrganization == null) return;
//        PropertyEditUtil.copyExclude(this, sysOrganization);
        String address = sysOrganization.getAddress();
        if (StringUtils.isNotBlank(address)) {
            String[] addressArr = address.split("\\$", 2);
            this.areaCode = addressArr[0];
            this.detailedAddress = addressArr[1];
        }

    }

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    /**
     * 转成组织机构
     *
     * @return
     */
    public SysOrganization toSysOrganization() {
        //拼接地址
        List<String> address = new ArrayList<>(2);
        address.add(areaCode);
        address.add(detailedAddress);
        String fullAddress = StringUtils.join(address, "$");
        this.setAddress(fullAddress);
        return this;
    }

    @Override
    public int compareTo(SysOrganizationModel o) {
//        return (int) (this.createTime.getTime() - o.getCreateTime().getTime());
//        return o.createTime.before(this.createTime) ? -1 : 1;
//        return o.createTime.compareTo(this.createTime);
        return 1;
    }

    /**
     * 获取省份
     * @return
     */
    @Override
    public String getProvince() {
        if(StringUtils.isBlank(areaCode) || areaCode.length() < 2){
            return "";
        }
        return areaCode.substring(0,2);
    }

    /**
     * 获取城市
     * @return
     */
    @Override
    public String getCity() {
        if(StringUtils.isBlank(areaCode) || areaCode.length() < 4){
            return "";
        }
        return areaCode.substring(2,4);
    }

    /**
     * 获取区域
     * @return
     */
    public String getArea() {
        if(StringUtils.isBlank(areaCode) || areaCode.length() < 6){
            return "";
        }
        return areaCode.substring(4,6);
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
