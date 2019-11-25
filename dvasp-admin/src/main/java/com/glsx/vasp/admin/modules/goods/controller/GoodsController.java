package com.glsx.vasp.admin.modules.goods.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.glsx.vasp.admin.modules.CommonController;
import com.glsx.vasp.admin.modules.goods.entity.Goods;
import com.glsx.vasp.admin.modules.goods.service.GoodsService;
import com.glsx.vasp.annotation.SysLog;
import com.glsx.vasp.enums.SysContants;
import com.glsx.vasp.validator.ValidatorUtils;
import com.glsx.vasp.validator.group.AddGroup;
import com.glsx.vasp.validator.group.UpdateGroup;
import com.glsx.vasp.web.Pagination;
import com.glsx.vasp.web.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 3/29/2019 12:49
 * @since 1.0.0
 */
@RestController
@RequestMapping("/goods")
public class GoodsController extends CommonController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 商品查询
     */
    @RequestMapping("/search")
    public R search(@RequestParam Map<String, Object> params, Pagination pagination) {
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        List<Goods> pages = goodsService.search(params);
        long total = ((Page<Goods>) pages).getTotal();
        return R.ok().putPageData(pages, total);
    }

    /**
     * 商品列表
     */
    @RequestMapping("/list")
    public R list() {
        List<Goods> list = goodsService.list();
        return R.ok().put("list", list);
    }

    /**
     * 商品列表by商品分类
     */
    @RequestMapping("/listByCategory/{categoryCode}")
    public R queryByCategory(@PathVariable("categoryCode") String code) {
        List<Goods> list = goodsService.queryByCategory(code);
        return R.ok().put("list", list);
    }

    /**
     * 商品信息
     */
    @GetMapping("/info/{goodsId}")
    public R info(@PathVariable("goodsId") Integer goodsId) {
        Goods goods = goodsService.getById(goodsId);
        goods.setGoodsPictures(goodsService.listGoodsPicturesByGoodsId(goodsId));
        return R.ok().put("goods", goods);
    }

    /**
     * 配置商品
     */
    @SysLog("配置商品")
    @RequestMapping(value = "/setting", method = RequestMethod.POST)
    public R setting(Integer goodsId, String desc) {
        Goods goods = goodsService.getById(goodsId);
        goods.setDetails(desc);
        goodsService.update(goods);
        return R.ok();
    }

    /**
     * 添加商品
     */
    @SysLog("添加商品")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public R save(@ModelAttribute Goods goods) {
        ValidatorUtils.validateEntity(goods, AddGroup.class);

        Goods goods1 = goodsService.getByName(goods.getName());
        if (goods1 != null) return R.error("该商品已存在！");

        goods.setOrgId(getOrgId());
        goods.setEnableStatus(SysContants.EnableStatus.disable.getCode());//默认不上架
        goods.setCreateUser(getUserId());
        goods.setCreateTime(new Date());
        goodsService.save(goods);
        return R.ok();
    }

    /**
     * 修改商品
     */
    @SysLog("修改商品")
    @RequestMapping("/update")
    public R update(@ModelAttribute Goods goods) {
        ValidatorUtils.validateEntity(goods, UpdateGroup.class);

        Goods goods1 = goodsService.getByName(goods.getName());
        if (goods1 != null && !goods1.getId().equals(goods.getId())) return R.error("该商品已存在！");

        goodsService.update(goods);
        return R.ok();
    }

    /**
     * 上架商品
     */
    @SysLog("上架商品")
    @RequestMapping(value = "/putOnShelves", method = RequestMethod.POST)
    public R putOnShelves(@ModelAttribute Goods goods) {
        ValidatorUtils.validateEntity(goods, UpdateGroup.class);

        Goods goods1 = goodsService.getByName(goods.getName());
        if (goods1 != null && !goods1.getId().equals(goods.getId())) return R.error("该商品已存在！");

        goods.setEnableStatus(SysContants.EnableStatus.enable.getCode());
        goodsService.update(goods);
        return R.ok();
    }

    /**
     * 下架商品
     */
    @SysLog("下架商品")
    @RequestMapping(value = "/pullOffShelves", method = RequestMethod.POST)
    public R pullOffShelves(Integer goodsId) {
        Goods goods = goodsService.getById(goodsId);
        goods.setEnableStatus(SysContants.EnableStatus.disable.getCode());
        goodsService.pullOffShelves(goods);
        return R.ok();
    }

}
