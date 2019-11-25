package com.glsx.vasp.admin.modules.sys.mapper;

import com.glsx.vasp.annotation.DataInitAspect;
import com.glsx.vasp.entity.SysArea;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DataInitAspect
public interface SysAreaMapper extends BaseMapper<SysArea> {

    List<SysArea> selectAllAreas();

    List<SysArea> selectAreasByCityCode(@Param("cityCode") String cityCode);

    SysArea selectByCode(@Param("code") String code);

}