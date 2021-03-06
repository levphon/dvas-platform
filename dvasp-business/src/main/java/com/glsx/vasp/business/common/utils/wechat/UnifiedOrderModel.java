/**
 * Copyright (C), 2015-2018, 广联赛讯有限公司
 * FileName: UnifiedOrderModel
 * Author:   liuyf
 * Date:     2018/5/17 13:59
 * Description: 微信支付预订单Modle
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.glsx.vasp.business.common.utils.wechat;

/**
 * 〈一句话功能简述〉<br>
 * 〈微信支付预订单Model〉
 *
 * @author liuyf
 * @create 2018/5/17
 * @since 1.0.0
 */
public class UnifiedOrderModel {

    private String body;
    private Integer totalFee;
    private String loanNo;
    private Integer repaymentPeriod;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public Integer getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setRepaymentPeriod(Integer repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

}