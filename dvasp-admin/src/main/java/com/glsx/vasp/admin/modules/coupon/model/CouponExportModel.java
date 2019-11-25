package com.glsx.vasp.admin.modules.coupon.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/16 16:14
 * @since 1.0.0
 */
public class CouponExportModel extends BaseRowModel {

    //需求文档V1.0.0导出：门店名称、优惠劵名称、用劵商品、用劵数量、利润、使用门槛

    @ExcelProperty(value = "门店名称", index = 0)
    private String storeName;

    @ExcelProperty(value = "优惠劵名称", index = 1)
    private String couponTitle;

    @ExcelProperty(value = "用劵商品", index = 2)
    private String goodsName;

    @ExcelProperty(value = "用劵数量", index = 3)
    private Integer usedQuantity;

    @ExcelProperty(value = "利润", index = 4)
    private BigDecimal profit;

    @ExcelProperty(value = "使用门槛", index = 5)
    private String usedThreshold;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getUsedQuantity() {
        return usedQuantity;
    }

    public void setUsedQuantity(Integer usedQuantity) {
        this.usedQuantity = usedQuantity;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getUsedThreshold() {
        return usedThreshold;
    }

    public void setUsedThreshold(String usedThreshold) {
        this.usedThreshold = usedThreshold;
    }

}
