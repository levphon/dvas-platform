package com.glsx.vasp.admin.modules.sys.service;

import com.glsx.vasp.admin.modules.sys.mapper.SysAreaMapper;
import com.glsx.vasp.admin.modules.sys.mapper.SysCityMapper;
import com.glsx.vasp.admin.modules.sys.mapper.SysProvinceMapper;
import com.glsx.vasp.entity.SysArea;
import com.glsx.vasp.entity.SysCity;
import com.glsx.vasp.entity.SysProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/8 15:32
 * @since 1.0.0
 */
@Service
public class InitService {

    @Autowired
    private SysProvinceMapper sysProvinceMapper;

    @Autowired
    private SysCityMapper sysCityMapper;

    @Autowired
    private SysAreaMapper sysAreaMapper;

    public List<SysProvince> listAllProvinces() {
        return sysProvinceMapper.selectAllProvinces();
    }

    public List<SysCity> listAllCities() {
        return sysCityMapper.selectAllCities();
    }

    public List<SysArea> listAllAreas() {
        return sysAreaMapper.selectAllAreas();
    }

}
