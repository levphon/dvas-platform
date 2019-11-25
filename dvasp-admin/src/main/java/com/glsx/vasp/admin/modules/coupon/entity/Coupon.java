package com.glsx.vasp.admin.modules.coupon.entity;

import com.glsx.vasp.entity.BaseEntity;
import com.glsx.vasp.validator.group.AddGroup;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "d_coupon")
public class Coupon extends BaseEntity {

    /**
     * 优惠券标题(有图片则显示图片)
     */
    @NotBlank(message = "优惠劵名称不能为空", groups = {AddGroup.class})
    private String title;

    /**
     * 图片
     */
    private String icon;

    /**
     * 可用于：10店铺优惠券 11新人店铺券  20商品优惠券  30类目优惠券  60平台优惠券 61新人平台券
     */
    private Integer used;

    /**
     * 1抵用券 2满减券 3无门槛券（需要限制大小）
     */
    private Integer type;

    /**
     * 1可用于特价商品 2不能 默认不能(商品优惠卷除外)
     */
    @Column(name = "with_special")
    private Integer withSpecial;

    /**
     * 店铺或商品流水号
     */
    @Column(name = "with_sn")
    private String withSn;

    /**
     * 满多少金额
     */
    @Column(name = "with_amount")
    private BigDecimal withAmount;

    /**
     * 券成本价
     */
    @NotNull(message = "劵支付金额不能为空", groups = {AddGroup.class})
    @Column(name = "cost_amount")
    private BigDecimal costAmount;

    /**
     * 用券金额
     */
    @NotNull(message = "劵抵用金额不能为空", groups = {AddGroup.class})
    @Column(name = "used_amount")
    private BigDecimal usedAmount;

    /**
     * 单个（商品）使用配额：针对单个商品、服务等使用券的个数最大限制
     */
    private Integer quota;

    /**
     * 总配额：发（行）券数量
     */
    @Column(name = "total_quota")
    private Integer totalQuota;

    /**
     * 已领取的优惠券数量
     */
    @Column(name = "take_count")
    private Integer takeCount;

    /**
     * 已使用的优惠券数量
     */
    @Column(name = "used_count")
    private Integer usedCount;

    /**
     * 发放开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 发放结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 时效:1绝对时效（领取后XXX-XXX时间段有效），2相对时效（领取后N天有效）
     */
    @Column(name = "valid_type")
    private Integer validType;

    /**
     * 使用开始时间
     */
    @Column(name = "valid_start_time")
    private Date validStartTime;

    /**
     * 使用结束时间
     */
    @Column(name = "valid_end_time")
    private Date validEndTime;

    /**
     * 自领取之日起有效天数
     */
    @Column(name = "valid_days")
    private Integer validDays;

    /**
     * 单个商品用券数量
     */
    @Column(name = "used_quantity")
    private Integer usedQuantity;

    /**
     * 使用门槛：1无使用门槛，2需做金融
     */
    @Column(name = "used_threshold")
    private Integer usedThreshold;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属商家（sp）
     */
    @Column(name = "org_id")
    private Integer orgId;


    /**
     * 1生效 2失效 3已结束
     */
//    private Integer enableStatus;

    /**
     * 获取优惠券标题(有图片则显示图片)
     *
     * @return title - 优惠券标题(有图片则显示图片)
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置优惠券标题(有图片则显示图片)
     *
     * @param title 优惠券标题(有图片则显示图片)
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取图片
     *
     * @return icon - 图片
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图片
     *
     * @param icon 图片
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取可用于：10店铺优惠券 11新人店铺券  20商品优惠券  30类目优惠券  60平台优惠券 61新人平台券
     *
     * @return used - 可用于：10店铺优惠券 11新人店铺券  20商品优惠券  30类目优惠券  60平台优惠券 61新人平台券
     */
    public Integer getUsed() {
        return used;
    }

    /**
     * 设置可用于：10店铺优惠券 11新人店铺券  20商品优惠券  30类目优惠券  60平台优惠券 61新人平台券
     *
     * @param used 可用于：10店铺优惠券 11新人店铺券  20商品优惠券  30类目优惠券  60平台优惠券 61新人平台券
     */
    public void setUsed(Integer used) {
        this.used = used;
    }

    /**
     * 获取1抵用券 2满减券 3无门槛券（需要限制大小）
     *
     * @return type - 1抵用券 2满减券 3无门槛券（需要限制大小）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1抵用券 2满减券 3无门槛券（需要限制大小）
     *
     * @param type 1抵用券 2满减券 3无门槛券（需要限制大小）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取1可用于特价商品 2不能 默认不能(商品优惠卷除外)
     *
     * @return with_special - 1可用于特价商品 2不能 默认不能(商品优惠卷除外)
     */
    public Integer getWithSpecial() {
        return withSpecial;
    }

    /**
     * 设置1可用于特价商品 2不能 默认不能(商品优惠卷除外)
     *
     * @param withSpecial 1可用于特价商品 2不能 默认不能(商品优惠卷除外)
     */
    public void setWithSpecial(Integer withSpecial) {
        this.withSpecial = withSpecial;
    }

    /**
     * 获取店铺或商品流水号
     *
     * @return with_sn - 店铺或商品流水号
     */
    public String getWithSn() {
        return withSn;
    }

    /**
     * 设置店铺或商品流水号
     *
     * @param withSn 店铺或商品流水号
     */
    public void setWithSn(String withSn) {
        this.withSn = withSn;
    }

    /**
     * 获取满多少金额
     *
     * @return with_amount - 满多少金额
     */
    public BigDecimal getWithAmount() {
        return withAmount;
    }

    /**
     * 设置满多少金额
     *
     * @param withAmount 满多少金额
     */
    public void setWithAmount(BigDecimal withAmount) {
        this.withAmount = withAmount;
    }

    /**
     * 获取成本价
     *
     * @return cost_price - 成本价
     */
    public BigDecimal getCostAmount() {
        return costAmount;
    }

    /**
     * 设置成本价
     *
     * @param costAmount 成本价
     */
    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    /**
     * 获取用券金额
     *
     * @return used_amount - 用券金额
     */
    public BigDecimal getUsedAmount() {
        return usedAmount;
    }

    /**
     * 设置用券金额
     *
     * @param usedAmount 用券金额
     */
    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getTotalQuota() {
        return totalQuota;
    }

    public void setTotalQuota(Integer totalQuota) {
        this.totalQuota = totalQuota;
    }

    /**
     * 获取已领取的优惠券数量
     *
     * @return take_count - 已领取的优惠券数量
     */
    public Integer getTakeCount() {
        return takeCount;
    }

    /**
     * 设置已领取的优惠券数量
     *
     * @param takeCount 已领取的优惠券数量
     */
    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }

    /**
     * 获取已使用的优惠券数量
     *
     * @return used_count - 已使用的优惠券数量
     */
    public Integer getUsedCount() {
        return usedCount;
    }

    /**
     * 设置已使用的优惠券数量
     *
     * @param usedCount 已使用的优惠券数量
     */
    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    /**
     * 获取发放开始时间
     *
     * @return start_time - 发放开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置发放开始时间
     *
     * @param startTime 发放开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取发放结束时间
     *
     * @return end_time - 发放结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置发放结束时间
     *
     * @param endTime 发放结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取时效:1绝对时效（领取后XXX-XXX时间段有效），2相对时效（领取后N天有效）
     *
     * @return valid_type - 时效:1绝对时效（领取后XXX-XXX时间段有效），2相对时效（领取后N天有效）
     */
    public Integer getValidType() {
        return validType;
    }

    /**
     * 设置时效:1绝对时效（领取后XXX-XXX时间段有效），2相对时效（领取后N天有效）
     *
     * @param validType 时效:1绝对时效（领取后XXX-XXX时间段有效），2相对时效（领取后N天有效）
     */
    public void setValidType(Integer validType) {
        this.validType = validType;
    }

    /**
     * 获取使用开始时间
     *
     * @return valid_start_time - 使用开始时间
     */
    public Date getValidStartTime() {
        return validStartTime;
    }

    /**
     * 设置使用开始时间
     *
     * @param validStartTime 使用开始时间
     */
    public void setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
    }

    /**
     * 获取使用结束时间
     *
     * @return valid_end_time - 使用结束时间
     */
    public Date getValidEndTime() {
        return validEndTime;
    }

    /**
     * 设置使用结束时间
     *
     * @param validEndTime 使用结束时间
     */
    public void setValidEndTime(Date validEndTime) {
        this.validEndTime = validEndTime;
    }

    /**
     * 获取自领取之日起有效天数
     *
     * @return valid_days - 自领取之日起有效天数
     */
    public Integer getValidDays() {
        return validDays;
    }

    /**
     * 设置自领取之日起有效天数
     *
     * @param validDays 自领取之日起有效天数
     */
    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

}