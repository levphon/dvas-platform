package com.glsx.vasp.business.modules.areas.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.entity.SysArea;
import com.glsx.vasp.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *
 * @author liumh
 * @date 2019-04-03 11:33:49
 */
@Component
public interface BaseAreasMapper extends BaseMapper<SysArea> {

    @DataPermAspect(showSql = true, tablePerix = "ba")
    List<SysArea> search(Map<String,String> params);
}
