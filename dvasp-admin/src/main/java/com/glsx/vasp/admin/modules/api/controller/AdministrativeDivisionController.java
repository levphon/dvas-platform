package com.glsx.vasp.admin.modules.api.controller;

import com.glsx.vasp.entity.SysArea;
import com.glsx.vasp.entity.SysCity;
import com.glsx.vasp.entity.SysProvince;
import com.glsx.vasp.admin.modules.sys.service.AdministrativeDivisionService;
import com.glsx.vasp.web.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 行政区域
 *
 * @author payu
 * @create 2019/4/2 20:56
 * @since 1.0.0
 */
@RestController
@RequestMapping("/base")
public class AdministrativeDivisionController {

    @Autowired
    private AdministrativeDivisionService administrativeDivisionService;

    /**
     * 省份
     */
    @GetMapping("/provinces")
    public R listProvince() {
        List<SysProvince> list = administrativeDivisionService.listProvinces();
        return R.ok().put("list", list);
    }

    /**
     * 地市
     */
    @GetMapping("/cities")
    public R listCity(@RequestParam("provinceCode") String provinceCode) {
        List<SysCity> list = administrativeDivisionService.listCitiesByProvinceCode(provinceCode);
        return R.ok().put("list", list);
    }

    /**
     * 辖区
     */
    @GetMapping("/areas")
    public R listArea(@RequestParam("cityCode") String cityCode) {
        List<SysArea> list = administrativeDivisionService.listAreasByCityCode(cityCode);
        return R.ok().put("list", list);
    }

}
