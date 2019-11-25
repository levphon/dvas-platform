package com.glsx.vasp.admin.modules.coupon.service;

import com.glsx.vasp.admin.common.exception.AdminException;
import com.glsx.vasp.admin.common.utils.ShiroUtils;
import com.glsx.vasp.admin.modules.coupon.entity.Coupon;
import com.glsx.vasp.admin.modules.coupon.entity.CouponRecipients;
import com.glsx.vasp.admin.modules.coupon.mapper.CouponMapper;
import com.glsx.vasp.admin.modules.coupon.mapper.CouponRecipientsMapper;
import com.glsx.vasp.admin.modules.coupon.model.CouponExportModel;
import com.glsx.vasp.admin.modules.coupon.model.CouponGoodsVO;
import com.glsx.vasp.admin.modules.coupon.model.CouponSettingModel;
import com.glsx.vasp.admin.modules.coupon.model.CouponStoreVO;
import com.glsx.vasp.admin.modules.store.entity.Store;
import com.glsx.vasp.admin.modules.store.entity.StoreCouponConfig;
import com.glsx.vasp.admin.modules.store.entity.StoreGoodsConfig;
import com.glsx.vasp.admin.modules.store.mapper.StoreCouponConfigMapper;
import com.glsx.vasp.admin.modules.store.mapper.StoreGoodsConfigMapper;
import com.glsx.vasp.admin.modules.store.mapper.StoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/1 11:51
 * @since 1.0.0
 */
@Service
public class CouponService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private CouponRecipientsMapper couponRecipientsMapper;

    @Autowired
    private StoreGoodsConfigMapper storeGoodsConfigMapper;

    @Autowired
    private StoreCouponConfigMapper storeCouponConfigMapper;

    public List<CouponStoreVO> search(Map<String, Object> params) {
        return couponMapper.search(params);
    }

    public Long searchCount(Map<String, Object> params) {
        Long total = couponMapper.searchCount(params);
        if (total == null) return 0L;
        return total;
    }

    public List<CouponExportModel> export(Map<String, Object> params) {
        return couponMapper.export(params);
    }

    public List<CouponStoreVO> listByStoreId(Integer storeId) {
        return couponMapper.selectByStoreId(storeId);
    }

    public List<CouponGoodsVO> listByGoodsId(Map<String, Object> params) {
        List<CouponGoodsVO> cgvList = couponMapper.selectListByGoodsId(params);
        for (CouponGoodsVO cgv : cgvList) {
            String usedThreshold = "";
            if (cgv.getUsedThreshold() != null) {
                if (cgv.getUsedThreshold() == 1) {
                    usedThreshold = "（无使用门槛）";
                } else {
                    usedThreshold = "（需做金融）";
                }
            }
            cgv.setCouponTitle(cgv.getCouponTitle() + usedThreshold);
        }
        return cgvList;
    }

    public Coupon getById(Integer couponId) {
        return couponMapper.selectByPrimaryKey(couponId);
    }

    public CouponRecipients getCouponRecipientsById(Integer couponRecipientsId) {
        return couponRecipientsMapper.selectByPrimaryKey(couponRecipientsId);
    }

    public List<Coupon> getUnconfigedCoupons(Coupon coupon) {
        return couponMapper.selectUnconfigedByCouponInfo(coupon);
    }

    public List<Coupon> getConfigedCoupons(Coupon coupon) {
        return couponMapper.selectConfigedByCouponInfo(coupon);
    }

    public void save(Coupon coupon) {
        couponMapper.insert(coupon);
    }

    public int update(Coupon coupon) {
        return couponMapper.updateByPrimaryKeySelective(coupon);
    }

    /**
     * 配置门店优惠券
     *
     * @param coupon
     * @param model
     * @return
     */
    public void configCouponToStore(Coupon coupon, CouponSettingModel model) {
        // 券控制
        for (Integer storeId : model.getStoreIds()) {
            Store store = storeMapper.selectByPrimaryKey(storeId);
            //判断门店是否已经存在不做金融券\做金融券
            Coupon financeCoupon = couponMapper.selectCouponByStoreIdAndGoods(storeId, Integer.valueOf(coupon.getWithSn()), model.getUsedThreshold());
            if (financeCoupon != null)
                throw AdminException.create(String.format("%s券在%s门店已存在，如需修改券配置请删除后重新配置！", model.getUsedThreshold() == 1 ? "无使用门槛" : "需做金融", store.getName()));

            //判断门店券配置关系
            StoreCouponConfig couponConfig = storeCouponConfigMapper.selectByStoreIdAndCouponId(storeId, coupon.getId());
            if (couponConfig != null)
                throw AdminException.create(String.format("该种优惠券在%s门店已存在，如需修改券配置请删除后重新配置！", store.getName()));

            //检测门店是否已经有这种券
            List<Coupon> distributedCouponList = couponMapper.selectByConfigedCouponsByStoreId(storeId);
            for (Coupon dc : distributedCouponList) {
                if (!Objects.equals(coupon.getId(), dc.getId())) {
                    //相同商品、相同成本价、相同抵用金额   需判断是否做金融
                    if (coupon.getWithSn().equals(dc.getWithSn())
                            && coupon.getCostAmount().equals(dc.getCostAmount())
                            && coupon.getUsedAmount().equals(dc.getUsedAmount())
                            && coupon.getUsedThreshold().equals(dc.getUsedThreshold())) {

                        String usedThreshold = dc.getUsedThreshold() == 1 ? "" : "（需做金融）";
                        throw AdminException.create(String.format("门店%s的%s%s的券已经存在！", store.getName(), coupon.getTitle(), usedThreshold));
                    }
                }
            }
            //设置门店商品到店价
            this.insertOrUpdateStoreGoodsConfig(storeId, Integer.valueOf(coupon.getWithSn()), model.getGoodsShopPrice());

            //设置门店券配置关系
            this.insertOrUpdateStoreCouponConfig(couponConfig, storeId, coupon.getId());
        }
    }

    /**
     * 设置门店商品到店价
     *
     * @param storeId
     * @param goodsId
     * @param goodsShopPrice
     * @return
     */
    public int insertOrUpdateStoreGoodsConfig(Integer storeId, Integer goodsId, BigDecimal goodsShopPrice) {
        //如果直接从 券配置中心按钮进去  不是新增券进去，商品到店价为空
        if (goodsShopPrice == null) return 0;

        Date now = new Date();
        StoreGoodsConfig goodsConfig = storeGoodsConfigMapper.selectByStoreIdAndGoodsId(storeId, goodsId);
        if (goodsConfig == null) {
            goodsConfig = new StoreGoodsConfig();
            goodsConfig.setCreateUser(ShiroUtils.getUserId());
            goodsConfig.setCreateTime(now);
        }
        //设置门店商品到店价
        goodsConfig.setStoreId(storeId);
        goodsConfig.setGoodsId(goodsId);
        goodsConfig.setShopPrice(goodsShopPrice);
        goodsConfig.setUpdateUser(ShiroUtils.getUserId());
        if (goodsConfig.getId() == null) {
            return storeGoodsConfigMapper.insert(goodsConfig);
        } else {
            return storeGoodsConfigMapper.updateByPrimaryKeySelective(goodsConfig);
        }
    }

    /**
     * 设置门店券配置关系
     *
     * @param couponConfig
     * @param storeId
     * @param couponId
     * @return
     */
    private int insertOrUpdateStoreCouponConfig(StoreCouponConfig couponConfig, Integer storeId, Integer couponId) {
        Date now = new Date();
        if (couponConfig == null) {
            couponConfig = new StoreCouponConfig();
            couponConfig.setCreateUser(ShiroUtils.getUserId());
            couponConfig.setCreateTime(now);
        }
        couponConfig.setStoreId(storeId);
        couponConfig.setCouponId(couponId);
        couponConfig.setUpdateUser(ShiroUtils.getUserId());
        if (couponConfig.getId() == null) {
            return storeCouponConfigMapper.insert(couponConfig);
        } else {
            return storeCouponConfigMapper.updateByPrimaryKeySelective(couponConfig);
        }
    }

    public int logicDelete(Integer couponId) {
        //删除券的同时删除门店和券的关系数据
        int delSCCnt = storeCouponConfigMapper.deleteByCouponId(couponId);
        logger.info(String.format("删除券%d和门店关系记录数：%d", couponId, delSCCnt));
        return couponMapper.logicDelete(couponId);
    }

    public int deleteSCRelation(Integer scId) {
        return storeCouponConfigMapper.deleteSCRelation(scId);
    }

}
