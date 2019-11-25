package com.glsx.vasp.admin.modules.sys.mapper;

import com.glsx.vasp.annotation.DataInitAspect;
import com.glsx.vasp.entity.SysCity;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DataInitAspect
public interface SysCityMapper extends BaseMapper<SysCity> {

    List<SysCity> selectAllCities();

    List<SysCity> selectCitiesByProvinceCode(@Param("provinceCode") String provinceCode);

    SysCity selectByCode(@Param("code") String code);

}