package com.glsx.vasp.business.modules.cities.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.entity.SysCity;
import com.glsx.vasp.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 市区
 *
 * @author liumh
 * @date 2019-03-28 13:49:24
 */
@Component
public interface BaseCitiesMapper extends BaseMapper<SysCity> {

    /**
     * 查找城市
     * @param params
     * @return
     */
    @DataPermAspect(showSql = true, tablePerix = "bc")
    List<SysCity> search(Map<String, String> params);
}
