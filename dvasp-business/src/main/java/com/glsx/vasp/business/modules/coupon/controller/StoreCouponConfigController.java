package com.glsx.vasp.business.modules.coupon.controller;

import com.glsx.vasp.business.modules.coupon.entity.StoreCouponConfig;
import com.glsx.vasp.business.modules.coupon.service.StoreCouponConfigService;
import com.glsx.vasp.web.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



/**
 * (由平台分配用户)分配优惠券表
 *
 * @author liumh
 * @email ${email}
 * @date 2019-04-04 18:36:00
 */
@RestController
@RequestMapping("/coupondistribute")
public class StoreCouponConfigController {
    @Autowired
    private StoreCouponConfigService storeCouponConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        //PageUtils page = StoreCouponConfigService.queryPage(params);

        return R.ok().put("page", "");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        //StoreCouponConfigEntity StoreCouponConfig = StoreCouponConfigService.getById(id);

        return R.ok().put("StoreCouponConfig", "");
    }

    /**
     * 保存(优惠券领用)
     */
    @RequestMapping("/save")
    public R save(StoreCouponConfig StoreCouponConfig){

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody StoreCouponConfig StoreCouponConfig){
        //ValidatorUtils.validateEntity(StoreCouponConfig);
        //StoreCouponConfigService.updateById(StoreCouponConfig);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
       // StoreCouponConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
