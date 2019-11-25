package com.glsx.vasp.admin.common.init;

import com.glsx.vasp.admin.modules.sys.service.InitService;
import com.glsx.vasp.entity.SysArea;
import com.glsx.vasp.entity.SysCity;
import com.glsx.vasp.entity.SysProvince;
import com.glsx.vasp.framework.components.RedisCacheUtils;
import com.glsx.vasp.framework.constant.CacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class InitData {

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private InitService initService;

    @PostConstruct
    public void init() {
        loadProvinces();
        loadCities();
        loadAreas();
    }

    private void loadProvinces() {
        redisCacheUtils.deleteByPrex(CacheConstants.REDIS_PROVINCE_KEY);
        redisCacheUtils.delete(CacheConstants.REDIS_PROVINCES_KEY);

        List<SysProvince> list = initService.listAllProvinces();
        for (SysProvince province : list) {
            redisCacheUtils.setCacheObject(CacheConstants.REDIS_PROVINCE_KEY + province.getCode(), province, CacheConstants.DAYS_365, TimeUnit.DAYS);
        }
        redisCacheUtils.setCacheList(CacheConstants.REDIS_PROVINCES_KEY, list);
    }

    private void loadCities() {
        redisCacheUtils.deleteByPrex(CacheConstants.REDIS_CITY_KEY);
        redisCacheUtils.delete(CacheConstants.REDIS_CITIES_KEY);

        List<SysCity> list = initService.listAllCities();
        for (SysCity city : list) {
            redisCacheUtils.setCacheObject(CacheConstants.REDIS_CITY_KEY + city.getCode(), city, CacheConstants.DAYS_365, TimeUnit.DAYS);
        }
        redisCacheUtils.setCacheList(CacheConstants.REDIS_CITIES_KEY, list);
    }

    private void loadAreas() {
        redisCacheUtils.deleteByPrex(CacheConstants.REDIS_AREA_KEY);
        redisCacheUtils.delete(CacheConstants.REDIS_AREAS_KEY);

        List<SysArea> list = initService.listAllAreas();
        for (SysArea area : list) {
            redisCacheUtils.setCacheObject(CacheConstants.REDIS_AREA_KEY + area.getCode(), area, CacheConstants.DAYS_365, TimeUnit.DAYS);
        }
        redisCacheUtils.setCacheList(CacheConstants.REDIS_AREAS_KEY, list);
    }

}
