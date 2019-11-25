package com.glsx.vasp.business.modules.storeGoods.controller;

import com.glsx.vasp.business.modules.storeGoods.entity.StoreGoodsConfig;
import com.glsx.vasp.business.modules.storeGoods.service.StoreGoodsConfigService;
import com.glsx.vasp.web.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



/**
 * (由平台)配置到门店的商品到店价
 *
 * @author liumh
 * @date 2019-04-15 14:56:01
 */
@RestController
@RequestMapping("goods/storegoodsconfig")
public class StoreGoodsConfigController {
    @Autowired
    private StoreGoodsConfigService storeGoodsConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        //PageUtils page = storeGoodsConfigService.queryPage(params);

        return R.ok().put("page", "");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        //StoreGoodsConfig storeGoodsConfig = storeGoodsConfigService.getById(id);

        return R.ok().put("storeGoodsConfig", "");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody StoreGoodsConfig storeGoodsConfig){
        //storeGoodsConfigService.save(storeGoodsConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody StoreGoodsConfig storeGoodsConfig){
       // ValidatorUtils.validateEntity(storeGoodsConfig);
        //storeGoodsConfigService.updateById(storeGoodsConfig);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
       // storeGoodsConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
