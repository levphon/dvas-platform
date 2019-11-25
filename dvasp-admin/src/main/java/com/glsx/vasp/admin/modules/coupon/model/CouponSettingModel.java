package com.glsx.vasp.admin.modules.coupon.model;

import com.glsx.vasp.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/3 13:51
 * @since 1.0.0
 */
public class CouponSettingModel {

    /**
     * 商品id
     */
//    @NotNull(message = "商品不能为空", groups = {UpdateGroup.class})
    private Integer goodsId;

    /**
     * 商品到店价
     */
    private BigDecimal goodsShopPrice;

    /**
     * 优惠券id
     */
    @NotNull(message = "优惠劵不能为空", groups = {UpdateGroup.class})
    private Integer couponId;

    /**
     * 用券数量
     */
    @NotNull(message = "用券数量不能为空", groups = {UpdateGroup.class})
    private Integer usedQuantity;

    /**
     * 使用门槛：1无使用门槛，2需做金融
     */
    @NotNull(message = "使用门槛不能为空", groups = {UpdateGroup.class})
    private Integer usedThreshold;

    /**
     * 门店ids
     */
    @NotNull(message = "门店不能为空", groups = {UpdateGroup.class})
    private Integer[] storeIds;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getGoodsShopPrice() {
        return goodsShopPrice;
    }

    public void setGoodsShopPrice(BigDecimal goodsShopPrice) {
        this.goodsShopPrice = goodsShopPrice;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getUsedQuantity() {
        return usedQuantity;
    }

    public void setUsedQuantity(Integer usedQuantity) {
        this.usedQuantity = usedQuantity;
    }

    public Integer getUsedThreshold() {
        return usedThreshold;
    }

    public void setUsedThreshold(Integer usedThreshold) {
        this.usedThreshold = usedThreshold;
    }

    public Integer[] getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(Integer[] storeIds) {
        this.storeIds = storeIds;
    }
}
