package com.glsx.vasp.admin.modules.coupon.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.pagehelper.PageHelper;
import com.glsx.vasp.admin.common.utils.ShiroUtils;
import com.glsx.vasp.admin.modules.CommonController;
import com.glsx.vasp.admin.modules.coupon.entity.Coupon;
import com.glsx.vasp.admin.modules.coupon.model.CouponExportModel;
import com.glsx.vasp.admin.modules.coupon.model.CouponGoodsVO;
import com.glsx.vasp.admin.modules.coupon.model.CouponSettingModel;
import com.glsx.vasp.admin.modules.coupon.model.CouponStoreVO;
import com.glsx.vasp.admin.modules.coupon.service.CouponService;
import com.glsx.vasp.admin.modules.goods.entity.Goods;
import com.glsx.vasp.admin.modules.goods.service.GoodsService;
import com.glsx.vasp.annotation.SysLog;
import com.glsx.vasp.enums.MimeType;
import com.glsx.vasp.utils.DateUtils;
import com.glsx.vasp.validator.ValidatorUtils;
import com.glsx.vasp.validator.group.AddGroup;
import com.glsx.vasp.validator.group.UpdateGroup;
import com.glsx.vasp.web.Pagination;
import com.glsx.vasp.web.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/1 11:50
 * @since 1.0.0
 */
@RestController
@RequestMapping("/coupon")
public class CouponController extends CommonController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 优惠券查询
     */
    @SysLog("优惠券查询")
    @RequestMapping("/search")
    public R search(@RequestParam Map<String, Object> params, Pagination pagination) {
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize(), false);
        List<CouponStoreVO> pages = couponService.search(params);
//        long total = ((Page<CouponStoreVO>) pages).getTotal();
        Long total = couponService.searchCount(params);
        return R.ok().putPageData(pages, total);
    }

    /**
     * 门店优惠券导出
     */
    @SysLog("门店优惠券导出")
    @RequestMapping("/export")
    public void export(HttpServletResponse response, @RequestParam Map<String, Object> params) throws IOException {
        OutputStream out = response.getOutputStream();
        String fileName = "券规则" + DateUtils.format(new Date());
        response.setContentType(MimeType.EXCEL2007.getContentType());
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        String encodeFileName = encodeFilename(fileName) + "." + MimeType.EXCEL2007.getSuffix();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + encodeFileName + "\"");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, CouponExportModel.class);
        sheet1.setSheetName(fileName);
        List<CouponExportModel> data = couponService.export(params);
        writer.write(data, sheet1);
        writer.finish();
    }

    /**
     * 优惠券列表by店面id
     */
    @SysLog("查询门店优惠券列表")
    @RequestMapping("/listcoupons/{storeId}")
    public R listByStoreId(@PathVariable("storeId") Integer storeId) {
        List<CouponStoreVO> list = couponService.listByStoreId(storeId);
        return R.ok().put("couponList", list);
    }

    /**
     * 优惠券列表
     */
    @SysLog("查询门店商品优惠券")
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        List<CouponGoodsVO> list = couponService.listByGoodsId(params);
        return R.ok().put("list", list);
    }

    /**
     * 生成优惠券
     */
    @SysLog("生成优惠券")
    @RequestMapping(value = "/gen", method = RequestMethod.POST)
    public R setting(@ModelAttribute Coupon coupon) {
        ValidatorUtils.validateEntity(coupon, AddGroup.class);

        //相同商品的优惠券最多只有两张，一张不做金融产品的和一张做金融产品的
        Coupon where = new Coupon();
        where.setWithSn(coupon.getWithSn());
        List<Coupon> unconfigedCoupons = couponService.getUnconfigedCoupons(where);
        if (unconfigedCoupons.size() > 0) {
            Goods goods = goodsService.getById(Integer.valueOf(coupon.getWithSn()));
            String couponTitle = unconfigedCoupons.get(0).getTitle();
            return R.error(String.format("%s的%s券配置尚未完成，请先完成配置！", goods.getName(), couponTitle));
        }
        List<Coupon> configedCoupons = couponService.getConfigedCoupons(where);
        if (configedCoupons.size() >= 2) {
            Goods goods = goodsService.getById(Integer.valueOf(coupon.getWithSn()));
            return R.error(String.format("%s已经配置了无使用门槛券和需做金融券！", goods.getName()));
        }

        coupon.setUsed(20);
        coupon.setType(1);
        coupon.setOrgId(getOrgId());
        coupon.setCreateUser(ShiroUtils.getUserId());
        coupon.setCreateTime(new Date());
        couponService.save(coupon);
        return R.ok().put("couponId", coupon.getId());
    }

    /**
     * 配置优惠券
     */
    @SysLog("配置优惠券")
    @RequestMapping("/setting")
    public R generate(@ModelAttribute CouponSettingModel model) {
        ValidatorUtils.validateEntity(model, UpdateGroup.class);
        Coupon coupon = couponService.getById(model.getCouponId());
        coupon.setUsedQuantity(model.getUsedQuantity());
        coupon.setUsedThreshold(model.getUsedThreshold());
        coupon.setUpdateUser(ShiroUtils.getUserId());
        //配置券
        couponService.configCouponToStore(coupon, model);
        couponService.update(coupon);
        return R.ok();
    }

    /**
     * 删除优惠券
     */
    @SysLog("删除优惠券")
    @RequestMapping("/delete")
    public R delete(@RequestParam("couponId") Integer couponId) {
        couponService.logicDelete(couponId);
        return R.ok();
    }

    /**
     * 删除优惠券和门店关联信息
     */
    @SysLog("删除优惠券和门店关系")
    @RequestMapping("/deleteSCRelation")
    public R deleteSCRelation(@RequestParam("scId") Integer scId) {
        couponService.deleteSCRelation(scId);
        return R.ok();
    }

}
