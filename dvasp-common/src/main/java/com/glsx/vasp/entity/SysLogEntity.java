package com.glsx.vasp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志,不采用表来存储 since 2019/1/14 存储在 mongodb
 */
@Entity
@Table(name = "sys_log")
public class SysLogEntity implements Serializable {

    private String id;

    // 模块功能
    @Column(name = "modul", length = 255)
    private String modul;

    // 操作人
    @Column(name = "operator", length = 10)
    private Integer operator;

    // 请求参数
    @Column(name = "request_data", length = 512)
    private String requestData;

    /**
     * ip地址
     */
    @Column(name = "ip", length = 46)
    private String ip;

    // 创建时间
    @Column(name = "create_time", length = 19)
    private java.util.Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // setGet modul
    public void setModul(String modul) {
        this.modul = modul;
    }

    public String getModul() {
        return this.modul;
    }

    // setGet operator
    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Integer getOperator() {
        return this.operator;
    }

    // setGet requestData
    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getRequestData() {
        return this.requestData;
    }

    // setGet ip
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
