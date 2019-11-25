package com.glsx.vasp.business.modules.common.mapper;

import org.springframework.stereotype.Component;

@Component
public interface CommonMapper {
    /**
     * 获取序列号
     *
     * @param sequenceName
     * @return
     */
    public String getNextVal(String sequenceName);
}
