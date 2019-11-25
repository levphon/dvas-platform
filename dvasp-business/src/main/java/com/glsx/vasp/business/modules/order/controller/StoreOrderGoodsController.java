package com.glsx.vasp.business.modules.order.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.modules.AbstractController;
import com.glsx.vasp.business.modules.order.entity.StoreOrder;
import com.glsx.vasp.business.modules.order.entity.StoreOrderGoods;
import com.glsx.vasp.business.modules.order.model.OrderGoodsDetailModel;
import com.glsx.vasp.business.modules.order.model.StoreOrderGoodsModel;
import com.glsx.vasp.business.modules.order.service.StoreOrderGoodsService;
import com.glsx.vasp.business.modules.order.service.StoreOrderService;
import com.glsx.vasp.web.Pagination;
import com.glsx.vasp.web.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 订单商品关系表
 *
 * @author liumh
 * @date 2019-04-09 15:03:07
 */
@RestController
@RequestMapping("/orderGoods")
public class StoreOrderGoodsController extends AbstractController {
    @Autowired
    private StoreOrderGoodsService storeOrderGoodsService;
    @Autowired
    StoreOrderService storeOrderService;

    /**
     * 查询订单商品列表详情
     */
    @RequestMapping("/searchDetail")
    public R searchDetail(Pagination pagination, String orderNo) {
        Map<String, Object> orderGoodsParams = new HashMap<>();
        orderGoodsParams.put("orderNo", orderNo);
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        List<OrderGoodsDetailModel> list = storeOrderGoodsService.searchDetail(orderGoodsParams);

        long total = ((Page<OrderGoodsDetailModel>) list).getTotal();
        return R.ok().putPageData(list, total);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        //StoreOrderGoods storeOrderGoods = storeOrderGoodsService.getById(id);

        return R.ok().put("storeOrderGoods", "");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody StoreOrderGoods storeOrderGoods) {
        //storeOrderGoodsService.save(storeOrderGoods);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody StoreOrderGoods storeOrderGoods) {
        //ValidatorUtils.validateEntity(storeOrderGoods);
        //storeOrderGoodsService.updateById(storeOrderGoods);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(Integer id) {
        storeOrderGoodsService.deleteByIds(id);
        return R.ok();
    }

}
