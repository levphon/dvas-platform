package com.glsx.vasp.business.modules.province.controller;

import com.glsx.vasp.business.common.annotation.AuthCheck;
import com.glsx.vasp.business.common.exception.ResultCodeEnum;
import com.glsx.vasp.business.modules.province.service.BaseProvincesService;
import com.glsx.vasp.entity.SysProvince;
import com.glsx.vasp.framework.components.RedisCacheUtils;
import com.glsx.vasp.web.R;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 省份
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-03-28 10:17:01
 */
@RestController
@RequestMapping("sysProvince")
public class BaseProvincesController {
    @Autowired
    private BaseProvincesService baseProvinceService;

    @Autowired
    RedisCacheUtils redisCacheUtil;
    /**
     * 列表
     */
    @AuthCheck
    @RequestMapping("/list")
    public R list() {
        List<SysProvince> list  = baseProvinceService.list();
        if (CollectionUtils.isEmpty(list))
            R.error(ResultCodeEnum.PROVINCE_NOT_EXIST.getCode(), ResultCodeEnum.PROVINCE_NOT_EXIST.getMsg());
        return R.ok().put("list", list);
    }

}
