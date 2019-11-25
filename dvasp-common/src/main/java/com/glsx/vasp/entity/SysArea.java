package com.glsx.vasp.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "base_areas")
public class SysArea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty("主键")
    protected Integer id;

    /**
     * 地市code
     */
    private String code;

    /**
     * 地市名称
     */
    private String name;

    /**
     * 地市code
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 省份code
     */
    @Column(name = "province_code")
    private String provinceCode;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取地市code
     *
     * @return code - 地市code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置地市code
     *
     * @param code 地市code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取地市名称
     *
     * @return name - 地市名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置地市名称
     *
     * @param name 地市名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取地市code
     *
     * @return city_code - 地市code
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置地市code
     *
     * @param cityCode 地市code
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * 获取省份code
     *
     * @return province_code - 省份code
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 设置省份code
     *
     * @param provinceCode 省份code
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

}