package com.glsx.vasp.business.modules.order.controller;

import com.glsx.vasp.business.modules.order.entity.StoreOrderCoupons;
import com.glsx.vasp.business.modules.order.service.StoreOrderCouponsService;
import com.glsx.vasp.web.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



/**
 * 订单用券关系表
 *
 * @author liumh
 * @date 2019-04-09 14:52:40
 */
@RestController
@RequestMapping("order/storeordercoupons")
public class StoreOrderCouponsController {
    @Autowired
    private StoreOrderCouponsService storeOrderCouponsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        //PageUtils page = storeOrderCouponsService.queryPage(params);

        return R.ok().put("page", "");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        //StoreOrderCoupons storeOrderCoupons = storeOrderCouponsService.getById(id);

        return R.ok().put("storeOrderCoupons", "");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody StoreOrderCoupons storeOrderCoupons){
        //storeOrderCouponsService.save(storeOrderCoupons);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody StoreOrderCoupons storeOrderCoupons){
        //ValidatorUtils.validateEntity(storeOrderCoupons);
        //storeOrderCouponsService.updateById(storeOrderCoupons);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        //storeOrderCouponsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
