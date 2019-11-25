package com.glsx.vasp.business.modules.car.controller;

import com.glsx.biz.common.base.entity.Carbrand;
import com.glsx.biz.common.base.entity.Carseries;
import com.glsx.biz.common.base.entity.Cartype;
import com.glsx.biz.common.base.service.CarbrandService;
import com.glsx.biz.common.base.service.CarseriesService;
import com.glsx.biz.common.base.service.CartypeService;
import com.glsx.vasp.business.common.annotation.AuthCheck;
import com.glsx.vasp.web.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/19 10:28
 * @since 1.0.0
 */
@RestController
@RequestMapping("/car")
public class CarBrandSeriesTypeController {

    @Autowired
    private CarbrandService carbrandService;

    @Autowired
    private CarseriesService carseriesService;

    @Autowired
    private CartypeService cartypeService;

    @AuthCheck
    @GetMapping("/get-brand/{param}")
    public R getBrands(@PathVariable("param") Object param) {
        List<Carbrand> list = carbrandService.getAllList();
        return R.ok().put("list", list);
    }

    @AuthCheck
    @GetMapping("/get-series/{brandId}")
    public R getSeries(@PathVariable("brandId") Integer brandId) {
        List<Carseries> list = carseriesService.getByCarbrandId(brandId);
        return R.ok().put("list", list);
    }

    @AuthCheck
    @GetMapping("/get-type/{seiesId}")
    public R getType(@PathVariable("seiesId") Integer seiesId) {
        List<Cartype> list = cartypeService.getBySeiesId(seiesId);
        return R.ok().put("list", list);
    }

}
