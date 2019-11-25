package com.glsx.vasp.business.modules.user.controller;


import com.glsx.vasp.business.common.annotation.AuthCheck;
import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.common.exception.ResultCodeEnum;
import com.glsx.vasp.business.common.utils.UserUtils;
import com.glsx.vasp.business.modules.AbstractController;
import com.glsx.vasp.business.modules.store.entity.Store;
import com.glsx.vasp.business.modules.store.service.StoreService;
import com.glsx.vasp.business.modules.user.entity.StoreUser;
import com.glsx.vasp.business.modules.user.service.StoreUserService;
import com.glsx.vasp.framework.components.RedisCacheUtils;
import com.glsx.vasp.web.R;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author qurong
 * @Title UserController.java
 * @Package com.qurong.controller
 * @Description 登录
 * @date 2018年10月24日 下午2:02:54
 */
@RestController
@RequestMapping(value = "/storeUser")
@Validated
public class StoreUserController extends AbstractController {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisCacheUtils redisCacheUtil;
    @Autowired
    private StoreUserService storeUserService;
    @Autowired
    private StoreService storeService;

    @AuthCheck
    @RequestMapping(value = "/login")
    public R login(@NotBlank(message = "手机号不能为空") String phone, @NotBlank(message = "验证码不能为空") String verifyCode, String miniOpenId) {
        logger.info("微信用户登录--》手机号phone=" + phone + ",验证码verifyCode=" + verifyCode + "小程序openId=" + miniOpenId);
        String smsKey = Constants.SMS_VERIFY_CODE_PREFIX + "." + phone;
        String realVerifyCode = (String) redisCacheUtil.getCacheObject(smsKey);
        if (StringUtils.isEmpty(realVerifyCode))
            return R.error(ResultCodeEnum.SMS_VERIFY_CODE_TIMEOUT.getCode(), ResultCodeEnum.SMS_VERIFY_CODE_TIMEOUT.getMsg());
        if (!verifyCode.equals(realVerifyCode))
            return R.error(ResultCodeEnum.SMS_VERIFY_CODE_NOT_RIGHT.getCode(), ResultCodeEnum.SMS_VERIFY_CODE_NOT_RIGHT.getMsg());
        StoreUser user = storeUserService.getUserByPhone(phone);
        if (user == null) user = new StoreUser();
        user.setPhone(phone);
        if (StringUtils.isNotEmpty(miniOpenId)) {
            //如果用户换手机号，miniopenid也要绑定到新手机号，并取消旧号码的关联
            StoreUser existUser = storeUserService.getUserByMiniOpenId(miniOpenId);
            if (existUser != null && !phone.equals(existUser.getPhone())) {
                existUser.setMiniOpenId(null);
                storeUserService.updateMiniOpenIdByPrimaryKey(existUser);
            }
            user.setMiniOpenId(miniOpenId);
        }
        int id = storeUserService.saveOrUpdate(user);
        user.setId(id);
        //miniOpenId保存到redis做免登录使用
        UserUtils.putSessionUser(session, user);
        redisCacheUtil.setCacheObject(Constants.REDIS_USER_PHONE_KEY + user.getPhone(), user, Constants.REDIS_INITDATA_VALID_TIME, TimeUnit.DAYS);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("user", user);
        resultMap.put("sessionId", session.getId());
        return R.ok().put("result", resultMap);
    }

    @AuthCheck
    @RequestMapping(value = "/loginByOpenId")
    public R loginOpenId(@NotBlank(message = "openId不能为空") String miniOpenId) {
        logger.info(miniOpenId);
        StoreUser dbUser = storeUserService.getUserByMiniOpenId(miniOpenId);
        //判断数据库是否存在
        if (dbUser == null)
            return R.error(ResultCodeEnum.USER_NOT_EXIST.getCode(), ResultCodeEnum.USER_NOT_EXIST.getMsg());

        StoreUser redisUser = (StoreUser) redisCacheUtil.getCacheObject(Constants.REDIS_USER_PHONE_KEY + dbUser.getPhone());
        //判断用户缓存是否存在
        if (redisUser == null)
            return R.error(ResultCodeEnum.NOT_REDIS_LOGIN.getCode(), ResultCodeEnum.NOT_REDIS_LOGIN.getMsg());
        //判断miniOpenid 是否存在并一致
        if (!miniOpenId.equals(redisUser.getMiniOpenId()))
            return R.error(ResultCodeEnum.NOT_MINIOPENID_LOGIN.getCode(), ResultCodeEnum.NOT_MINIOPENID_LOGIN.getMsg());

        UserUtils.putSessionUser(session, dbUser);
        redisCacheUtil.setCacheObject(Constants.REDIS_USER_PHONE_KEY + dbUser.getPhone(), dbUser, Constants.REDIS_INITDATA_VALID_TIME, TimeUnit.DAYS);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("dbUser", dbUser);
        resultMap.put("sessionId", session.getId());
        return R.ok().put("result", resultMap);
    }

    @RequestMapping(value = "/index")
    public R index() {
        //登录成功，有门店进入个人中心
        Store store = new Store();
        if (getStoreId() == null)
            return R.ok().data(store).put("code", ResultCodeEnum.STORE_NOT_EXIST.getCode()).put("msg", ResultCodeEnum.STORE_NOT_EXIST.getMsg());
        store = storeService.selectByPrimaryKey(getStoreId());
        if (store == null || store.getDelFlag() == Constants.IS_DEL)
            return R.ok().data(store).put("code", ResultCodeEnum.STORE_NOT_EXIST.getCode()).put("msg", ResultCodeEnum.STORE_NOT_EXIST.getMsg());
        if (store.getEnableStatus() == Constants.ENABLE_STATUS_CHECK)
            return R.ok().data(store).put("code", ResultCodeEnum.STORE_IS_CHECK.getCode()).put("msg", ResultCodeEnum.STORE_IS_CHECK.getMsg());
        if (store.getEnableStatus() == Constants.ENABLE_STATUS_INVALID)
            return R.ok().data(store).put("code", ResultCodeEnum.STORE_IS_STOP.getCode()).put("msg", ResultCodeEnum.STORE_IS_STOP.getMsg());
        return R.ok().data(store).put("code", 0).put("msg", "成功");
    }

    @RequestMapping(value = "/loginOut")
    public R loginOut() {
        StoreUser user = getSessionUser();
        redisCacheUtil.delete(Constants.REDIS_USER_PHONE_KEY + user.getPhone());
        UserUtils.clearSession(session);
        return R.ok().put("miniOpenId", user.getMiniOpenId());
    }

}
