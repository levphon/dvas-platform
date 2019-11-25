package com.glsx.vasp.business.modules.cities.controller;

import com.glsx.vasp.business.common.annotation.AuthCheck;
import com.glsx.vasp.business.common.exception.ResultCodeEnum;
import com.glsx.vasp.business.modules.cities.service.BaseCitiesService;
import com.glsx.vasp.entity.SysCity;
import com.glsx.vasp.web.R;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 市区
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-03-28 13:49:24
 */
@RestController
@RequestMapping("sysCity")
@Validated
public class BaseCitiesController {
    @Autowired
    private BaseCitiesService baseCitiesService;

    /**
     * 根据条件查询
     */
    @AuthCheck
    @RequestMapping("/searchByProvinceCode")
    public R searchByProvinceCode(@NotBlank(message = "省份编码不能为空") String provinceCode) {
        Map<String, String> params = new HashMap<>();
        params.put("provinceCode", provinceCode);
        List<SysCity>  list = baseCitiesService.searchByProvinceCode(params);
        if (CollectionUtils.isEmpty(list))
            R.error(ResultCodeEnum.CITY_NOT_EXIST.getCode(), ResultCodeEnum.CITY_NOT_EXIST.getMsg());
        return R.ok().put("list", list);
    }
}
