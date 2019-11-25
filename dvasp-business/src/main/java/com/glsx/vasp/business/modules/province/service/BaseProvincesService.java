package com.glsx.vasp.business.modules.province.service;

import com.glsx.vasp.business.modules.province.mapper.BaseProvincesMapper;
import com.glsx.vasp.entity.SysProvince;
import com.glsx.vasp.framework.components.RedisCacheUtils;
import com.glsx.vasp.framework.constant.CacheConstants;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
public class BaseProvincesService {

    @Autowired
    BaseProvincesMapper baseProvincesMapper;

    @Autowired
    RedisCacheUtils redisCacheUtil;

    public List<SysProvince> list() {
        List<SysProvince> list = redisCacheUtil.getCacheList(CacheConstants.REDIS_PROVINCES_KEY);
        //排序
        Collections.sort(list, Comparator.comparing(SysProvince::getCode));
        if (CollectionUtils.isEmpty(list)) list = baseProvincesMapper.selectAll();
        return list;
    }
}
