package com.glsx.vasp.admin.modules.sys.service;

import com.glsx.vasp.admin.modules.sys.mapper.SysAreaMapper;
import com.glsx.vasp.admin.modules.sys.mapper.SysCityMapper;
import com.glsx.vasp.admin.modules.sys.mapper.SysProvinceMapper;
import com.glsx.vasp.entity.SysArea;
import com.glsx.vasp.entity.SysCity;
import com.glsx.vasp.entity.SysProvince;
import com.glsx.vasp.framework.components.RedisCacheUtils;
import com.glsx.vasp.framework.constant.CacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/2 20:57
 * @since 1.0.0
 */
@Service
public class AdministrativeDivisionService {

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private SysProvinceMapper provinceMapper;

    @Autowired
    private SysCityMapper cityMapper;

    @Autowired
    private SysAreaMapper areaMapper;

    public List<SysProvince> listProvinces() {
        List<SysProvince> list = redisCacheUtils.getCacheList(CacheConstants.REDIS_PROVINCES_KEY);
        //排序
        Collections.sort(list, Comparator.comparing(SysProvince::getCode));
        return list;
    }

    public SysProvince getSysProvince(String code) {
        SysProvince province = (SysProvince) redisCacheUtils.getCacheObject(CacheConstants.REDIS_PROVINCE_KEY + code);
        if (province == null) return provinceMapper.selectByCode(code);
        return province;
    }

    public List<SysCity> listCities() {
        return redisCacheUtils.getCacheList(CacheConstants.REDIS_CITIES_KEY);
    }

    public List<SysCity> listCitiesByProvinceCode(String provinceCode) {
        List<SysCity> list = redisCacheUtils.getListByPrex(CacheConstants.REDIS_CITY_KEY + provinceCode);
        //排序
        Collections.sort(list, Comparator.comparing(SysCity::getCode));
        return list;
    }

    public SysCity getSysCity(String code) {
        SysCity city = (SysCity) redisCacheUtils.getCacheObject(CacheConstants.REDIS_CITY_KEY + code);
        if (city == null) return cityMapper.selectByCode(code);
        return city;
    }

    public List<SysArea> listAreas() {
        return redisCacheUtils.getCacheList(CacheConstants.REDIS_AREAS_KEY);
    }

    public List<SysArea> listAreasByCityCode(String cityCode) {
        List<SysArea> list = redisCacheUtils.getListByPrex(CacheConstants.REDIS_AREA_KEY + cityCode);
        //排序
        Collections.sort(list, Comparator.comparing(SysArea::getCode));
        return list;
    }

    public SysArea getSysArea(String code) {
        SysArea area = (SysArea) redisCacheUtils.getCacheObject(CacheConstants.REDIS_AREA_KEY + code);
        if (area == null) areaMapper.selectByCode(code);
        return area;
    }

    public String getAddress(String provinceCode, String cityCode, String areaCode) {
        SysProvince sysProvince = this.getSysProvince(provinceCode);
        SysCity sysCity = this.getSysCity(cityCode);
        SysArea sysArea = this.getSysArea(areaCode);
        return sysProvince.getName() + sysCity.getName() + sysArea.getName();
    }

    public String getAddressByArea(String areaCode) {
        SysArea sysArea = this.getSysArea(areaCode);
        SysProvince sysProvince = this.getSysProvince(sysArea.getProvinceCode());
        SysCity sysCity = this.getSysCity(sysArea.getCityCode());
        return sysProvince.getName() + sysCity.getName() + sysArea.getName();
    }

}
