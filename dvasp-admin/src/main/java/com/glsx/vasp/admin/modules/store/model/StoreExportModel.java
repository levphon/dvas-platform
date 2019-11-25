package com.glsx.vasp.admin.modules.store.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 3/27/2019 13:54
 * @since 1.0.0
 */
public class StoreExportModel extends BaseRowModel {

//   需求文档V1.0.0导出：门店名称、联系人、手机号、门店地址、创建时间、门店状态

    @ExcelProperty(value = "门店名称", index = 0)
    private String name;

    @ExcelProperty(value = "联系人", index = 1)
    private String contactor;

    @ExcelProperty(value = "手机号", index = 2)
    private String phone;

    @ExcelProperty(value = "门店地址", index = 3)
    private String fullAddress;

    @ExcelProperty(value = "创建时间", index = 4)
    private String createTime;

    @ExcelProperty(value = "门店状态", index = 5)
    private String enableStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus;
    }
}
