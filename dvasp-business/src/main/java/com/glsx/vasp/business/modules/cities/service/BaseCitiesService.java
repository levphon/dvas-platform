package com.glsx.vasp.business.modules.cities.service;

import com.glsx.vasp.business.modules.cities.mapper.BaseCitiesMapper;
import com.glsx.vasp.entity.SysCity;
import com.glsx.vasp.entity.SysProvince;
import com.glsx.vasp.framework.components.RedisCacheUtils;
import com.glsx.vasp.framework.constant.CacheConstants;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


@Service
public class BaseCitiesService {

    @Autowired
    BaseCitiesMapper baseCitiesMapper;
    @Autowired
    RedisCacheUtils redisCacheUtil;

    public List<SysCity> searchByProvinceCode(Map<String, String> params) {
        String provinceCode = params.get("provinceCode");
        List<SysCity> list = redisCacheUtil.getListByPrex(CacheConstants.REDIS_CITY_KEY + provinceCode);
        Collections.sort(list, Comparator.comparing(SysCity::getCode));
        if (CollectionUtils.isEmpty(list)) list = baseCitiesMapper.search(params);
        return list;
    }
}
