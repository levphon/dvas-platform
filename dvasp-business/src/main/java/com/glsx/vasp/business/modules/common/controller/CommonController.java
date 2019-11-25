package com.glsx.vasp.business.modules.common.controller;

import com.glsx.vasp.business.common.annotation.AuthCheck;
import com.glsx.vasp.business.common.exception.ResultCodeEnum;
import com.glsx.vasp.business.modules.common.service.CommonService;
import com.glsx.vasp.utils.StringUtils;
import com.glsx.vasp.web.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用请求处理
 *
 * @author qurong
 */
@RestController
@RequestMapping(value = "/common")
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;

    @AuthCheck
    @RequestMapping(value = "/sendVerificationCode")
    public R sendVerificationCode(String phone) {
        if (StringUtils.isEmpty(phone)) return R.error("手机号码不能为空!");
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String verifyCode = commonService.sendVerificationCode(phone);
            logger.info("手机号：" + phone + "的短信验证码：" + verifyCode);
            result.put("verifyCode", verifyCode);
            return R.ok().put("result", result);
        } catch (RedisSystemException e) {
            logger.error("获取验证码redis异常" + e.getMessage(), e);
            /*
             * 测试环境redis连接总被重试异常重试
             */
            String verifyCode = commonService.sendVerificationCode(phone);
            logger.info("手机号：" + phone + "的短信验证码：" + verifyCode);
            result.put("verifyCode", verifyCode);
            return R.ok().put("result", result);
        } catch (Exception e) {
            logger.error("获取验证码失败" + e.getMessage(), e);
            return R.error(ResultCodeEnum.ERROR.getCode(), ResultCodeEnum.ERROR.getMsg());
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView get(ModelAndView mv) {
        mv.setViewName("fileUpload");
        return mv;
    }
}
