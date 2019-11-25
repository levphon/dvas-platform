package com.glsx.vasp.business.modules.areas.service;

import com.glsx.vasp.business.modules.areas.mapper.BaseAreasMapper;
import com.glsx.vasp.entity.SysArea;
import com.glsx.vasp.entity.SysCity;
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
public class BaseAreasService{

    @Autowired
    BaseAreasMapper baseAreasMapper;
    @Autowired
    RedisCacheUtils redisCacheUtil;

    public List<SysArea> searchByCityCode(Map<String, String> params) {
        String cityCode = params.get("cityCode");
        List<SysArea> list = redisCacheUtil.getListByPrex(CacheConstants.REDIS_AREA_KEY+cityCode);
        Collections.sort(list, Comparator.comparing(SysArea::getCode));
        if (CollectionUtils.isEmpty(list)) list = baseAreasMapper.search(params);
        return list;
    }
}
