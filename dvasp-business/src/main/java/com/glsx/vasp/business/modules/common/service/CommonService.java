package com.glsx.vasp.business.modules.common.service;

import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.modules.common.mapper.CommonMapper;
import com.glsx.vasp.framework.components.RedisCacheUtils;
import com.glsx.vasp.framework.components.SmsUtils;
import com.glsx.vasp.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author qurong
 * @Title CommonServiceImpl.java
 * @Package com.qurong.service.impl
 * @Description TODO
 * @date 2018年10月22日 下午3:01:25
 */
@Service
public class CommonService {

    private Logger logger = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    private SmsUtils smsUtil;

    @Autowired
    CommonMapper commonMapper;

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisCacheUtils redisCacheUtil;

    @SuppressWarnings("unchecked")
    public String sendVerificationCode(String phone) {
        String smsKey = Constants.SMS_VERIFY_CODE_PREFIX + "." + phone;
        String smsCode = StringUtils.generateRandomCode(true, 4);
        redisCacheUtil.setCacheObject(smsKey, smsCode, Constants.SMS_VERIFY_CODE_TIMEOUT, TimeUnit.SECONDS);
        String msgContent = MessageFormat.format(Constants.USER_INFO_APPROVAL_SMS_CONTENT, smsCode);
        String result = "发送手机号phone=" + phone + ">>>smsCode=" + smsCode;
        logger.info(result);
        smsUtil.send(phone, msgContent);
        return smsCode;
    }


    public synchronized String getContractSerialNum(String sequenceName) {
        String nextVal = commonMapper.getNextVal(sequenceName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(new Date());
        return Constants.ORDER_NO_PREFIX + dateNowStr + StringUtils.zeroFill(nextVal, 6);
    }
}
