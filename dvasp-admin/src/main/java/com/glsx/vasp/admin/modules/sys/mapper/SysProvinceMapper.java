package com.glsx.vasp.admin.modules.sys.mapper;

import com.glsx.vasp.annotation.DataInitAspect;
import com.glsx.vasp.entity.SysProvince;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DataInitAspect
public interface SysProvinceMapper extends BaseMapper<SysProvince> {

    List<SysProvince> selectAllProvinces();

    SysProvince selectByCode(@Param("code") String code);

}