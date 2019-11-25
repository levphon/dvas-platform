package com.glsx.vasp.admin.modules.store.model;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/16 11:22
 * @since 1.0.0
 */
public class StoreRegionVO {

    /**
     * 门店id
     */
    private Integer id;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 门店区域code
     */
    private String regionCode;

    /**
     * 门店区域
     */
    private String regionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
