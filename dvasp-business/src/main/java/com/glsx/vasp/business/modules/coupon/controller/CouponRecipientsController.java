package com.glsx.vasp.business.modules.coupon.controller;

import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.modules.AbstractController;
import com.glsx.vasp.business.modules.coupon.entity.Coupon;
import com.glsx.vasp.business.modules.coupon.entity.CouponRecipients;
import com.glsx.vasp.business.modules.coupon.model.CouponRecipientsModel;
import com.glsx.vasp.business.modules.coupon.service.CouponRecipientsService;
import com.glsx.vasp.business.modules.coupon.service.CouponService;
import com.glsx.vasp.business.modules.goods.entity.Goods;
import com.glsx.vasp.business.modules.goods.service.GoodsService;
import com.glsx.vasp.business.modules.shopcar.service.ShopcarService;
import com.glsx.vasp.business.modules.storeGoods.entity.StoreGoodsConfig;
import com.glsx.vasp.business.modules.storeGoods.service.StoreGoodsConfigService;
import com.glsx.vasp.utils.StringUtils;
import com.glsx.vasp.web.R;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 由（平台）派发/（用户）领用优惠券表
 *
 * @author liumh
 * @date 2019-04-04 18:47:32
 */
@RestController
@RequestMapping("/couponRecipients")
public class CouponRecipientsController extends AbstractController {
    @Autowired
    private CouponRecipientsService couponRecipientsService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    ShopcarService shopcarService;
    @Autowired
    StoreGoodsConfigService storeGoodsConfigService;
    @Autowired
    CouponService couponService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //PageUtils page = couponRecipientsService.queryPage(params);

        return R.ok().put("page", "");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        //CouponRecipientsEntity couponRecipients = couponRecipientsService.getById(id);

        return R.ok().put("couponRecipients", "");
    }

    /**
     * 领用券
     *
     * @financeOrderNo 金融订单编号
     * @vinNo 车架号
     */
    @RequestMapping("/save")
    public R saveCouponRecipients(Integer id, String financeOrderNo, String vinNo) {
        logger.info(String.format("券id%s,金融订单号%s,车架号%s",id,financeOrderNo,vinNo));
        Coupon coupon = couponService.selectByPrimaryKey(id);
        //查询相同的券是否存在和是否使用
        Map<String, Object> couponParams = new HashMap<>();
        couponParams.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
        couponParams.put("goodsId", coupon.getWithSn());
        couponParams.put("usedFlag", Constants.NOT_USED);//未使用
        couponParams.put("userId", getUserId());
        List<CouponRecipients> list = couponRecipientsService.search(couponParams);
        CouponRecipients couponRecipients = new CouponRecipients();
        if (CollectionUtils.isNotEmpty(list)) {
            couponRecipients = list.get(0);
            logger.info(couponRecipients.toString());
        }
        couponRecipients.setUserId(getUserId());
        couponRecipients.setUsedFlag(Constants.NOT_USED);//券使用状态：1未使用，2已使用
        couponRecipients.setCreateTime(new Date());
        couponRecipients.setCouponId(id);
        couponRecipients.setCouponTitle(coupon.getTitle());
        couponRecipients.setGoodsId(Integer.parseInt(coupon.getWithSn()));
        couponRecipients.setCostAmount(coupon.getCostAmount());
        couponRecipients.setUsedAmount(coupon.getUsedAmount());
        couponRecipients.setUsedQuantity(coupon.getUsedQuantity());
        couponRecipients.setFinanceOrderNo(financeOrderNo==null?"":financeOrderNo);
        couponRecipients.setVinNo(vinNo==null?"":vinNo);
        couponRecipients.setUsedThreshold(coupon.getUsedThreshold());
        couponRecipientsService.saveOrUpdate(couponRecipients);
        Goods goods = goodsService.getById(Integer.parseInt(coupon.getWithSn()));
        logger.info("商品到店价格" + goods.getShopPrice());
        if (getStoreId() != null) {
            Map<String, Object> configParams = new HashMap<>();
            //configParams.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
            configParams.put("goodsId", goods.getId());
            configParams.put("storeId", getStoreId());
            List<StoreGoodsConfig> storeGoodsList = storeGoodsConfigService.search(configParams);
            if (CollectionUtils.isNotEmpty(storeGoodsList)) goods.setShopPrice(storeGoodsList.get(0).getShopPrice());
        }
        logger.info("商品到店价格" + goods.getShopPrice());
        CouponRecipientsModel couponRecipientsModel = new CouponRecipientsModel();
        BeanUtils.copyProperties(couponRecipients, couponRecipientsModel);
        couponRecipientsModel.setName(goods.getName());//商品名称
        //商品实际支付金额
        couponRecipientsModel.setPayAmount(goods.getShopPrice().subtract(couponRecipients.getUsedAmount().multiply(new BigDecimal(couponRecipients.getUsedQuantity()))));
        return R.ok().put("result", couponRecipientsModel);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CouponRecipients couponRecipients) {
        //ValidatorUtils.validateEntity(couponRecipients);
        //couponRecipientsService.updateById(couponRecipients);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        //couponRecipientsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
