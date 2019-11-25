package com.glsx.vasp.framework.components;

import com.alibaba.fastjson.JSONObject;
import com.glsx.vasp.utils.HttpUtils;
import com.glsx.vasp.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.TreeMap;

/**
 * 短信工具类
 */
@Component("smsUtil")
public class SmsUtils {

    private static Logger logger = LoggerFactory.getLogger(SmsUtils.class);

    /**
     * 短信派单
     *
     * @param phone
     * @param msgContent
     */
    public void send(String phone, String msgContent) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("method", "glsx.message.sendmessage");
        treeMap.put("format", "json");
        treeMap.put("akey", "9c991f5abac812b7a2739beecde2fdc7");
        treeMap.put("v", "1.0.0");
        treeMap.put("channel", "1001");
        treeMap.put("lock", "2AC16F3AB37C129E273A4979FD0A46B0");

        String source = PropertiesUtils.getProperty("sms.source");
        treeMap.put("source", source);
        treeMap.put("type", "1");
        treeMap.put("phone", phone);
        treeMap.put("content", msgContent);
        treeMap.put("subcode", "");

        logger.info("Send Sms:" + treeMap.toString());

        String url = PropertiesUtils.getProperty("sms.url");
        logger.info(url);
//        String url = "http://192.168.3.206:8118/router";//开发
//        String url = "http://qcdidicb.glsx.net:7060/router";//测试（貌似已经废弃了）
//        String url = "http://qcapp.oss.glsx.net:7060/gop-message/router";//测试

        HttpUtils httpUtils = new HttpUtils();
        try {
            String res = httpUtils.get(url, treeMap);
            logger.info("Result:" + res);
            if (StringUtils.isNotEmpty(res)) {
                if (res.contains("<html>")) {
                    logger.error("短信服务不可用，返回html...");
                }
                if (res.contains("{")) {
                    JSONObject jsonObject = JSONObject.parseObject(res);
                    String code = jsonObject.getString("code");
                    if (!code.equals("0")) {
                        res = httpUtils.get(url, treeMap);
                        logger.info("Failure resend result:" + res);
                    }
                }
            } else {
                logger.error("短信服务不可用，返回为空");
            }
        } catch (IllegalStateException ex) {
            if (StringUtils.isNotEmpty(url)) logger.error("IllegalStateException", ex);
        } catch (Exception ex) {
            if (StringUtils.isNotEmpty(url)) logger.error("SendSmsException", ex);
            try {
                String res1 = httpUtils.get(url, treeMap);
                logger.info("Exception resend result:" + res1);
            } catch (Exception e) {
                logger.error("ReSendSmsException", ex);
            }
        }
    }

    /**
     * 短信派单（营销短信）
     *
     * @param phone
     * @param msgContent
     * @param source
     */
    public void send(String phone, String msgContent, String source) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("method", "glsx.message.sendmessage");
        treeMap.put("format", "json");
        treeMap.put("akey", "9c991f5abac812b7a2739beecde2fdc7");
        treeMap.put("v", "1.0.0");
        treeMap.put("channel", "1001");
        treeMap.put("lock", "2AC16F3AB37C129E273A4979FD0A46B0");

        treeMap.put("source", source);
        treeMap.put("type", "1");
        treeMap.put("phone", phone);
        treeMap.put("content", msgContent);
        treeMap.put("subcode", "");

        logger.info("Send Sms:" + treeMap.toString());

        String url = PropertiesUtils.getProperty("sms.url");
//        String url = "http://192.168.3.206:8118/router";//开发
//        String url = "http://qcdidicb.glsx.net:7060/router";//测试（貌似已经废弃了）
//        String url = "http://qcapp.oss.glsx.net:7060/gop-message/router";//测试

        HttpUtils httpUtils = new HttpUtils();
        try {
            String res = httpUtils.get(url, treeMap);
            logger.info("Result:" + res);

            JSONObject jsonObject = JSONObject.parseObject(res);
            String code = jsonObject.getString("code");
            if (!code.equals("0")) {
                res = httpUtils.get(url, treeMap);
                logger.info("Failure resend result:" + res);
            }
        } catch (Exception ex) {
            logger.error("SendSmsException", ex);
            try {
                String res1 = httpUtils.get(url, treeMap);
                logger.info("Exception resend result:" + res1);
            } catch (Exception e) {
                logger.error("ReSendSmsException", ex);
            }
        }
    }

//    public static void main(String[] args) {
//        SmsUtil smsUtil = new SmsUtil();
//        String smsCode = StringUtils.generateRandomCode(true, 4);
//        String msgContent = MessageFormat.format(Constants.USER_INFO_APPROVAL_SMS_CONTENT, smsCode);
//        smsUtil.send("18576673306", msgContent);
//    }

}
