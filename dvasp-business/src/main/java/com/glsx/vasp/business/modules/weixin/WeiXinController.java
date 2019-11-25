package com.glsx.vasp.business.modules.weixin;

import com.glsx.vasp.business.common.annotation.AuthCheck;
import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.common.exception.ResultCodeEnum;
import com.glsx.vasp.business.common.utils.wechat.WechatUtils;
import com.glsx.vasp.business.modules.AbstractController;
import com.glsx.vasp.business.modules.user.entity.StoreUser;
import com.glsx.vasp.business.modules.user.service.StoreUserService;
import com.glsx.vasp.framework.components.PropertiesUtils;
import com.glsx.vasp.web.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Zheng Xiajun
 * @ClassName: WxPayController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2018年10月23日
 */
@RestController
@RequestMapping("weixin")
public class WeiXinController extends AbstractController {

    @Autowired
    private WechatUtils wechatUtils;

    @Autowired
    private StoreUserService storeUserService;

    private static final Logger logger = LoggerFactory.getLogger(WeiXinController.class);

    /**
     * 获取微信用户openid
     *
     * @param code
     * @param type   0微信公众号openid，1小程序openid
     * @param custId
     * @return
     * @throws Exception
     */
    @AuthCheck
    @RequestMapping("getWechatOpenId")
    public R getWechatOpenId(String code, Integer type, Integer custId) throws Exception {
        Integer sessionUserId = getUserId();
        //处理获取公众号openid时候拿不到当期登录用户的情况
        if (sessionUserId == null) sessionUserId = custId;

        String appid = null;
        String secret = null;
        if (Constants.PBULIC == type) {
            appid = PropertiesUtils.getProperty(Constants.WECHAT_APPID);
            secret = PropertiesUtils.getProperty(Constants.WECHAT_SECRET);
        } else if (Constants.MINI == type) {
            appid = PropertiesUtils.getProperty(Constants.WECHAT_MINI_APPID);
            secret = PropertiesUtils.getProperty(Constants.WECHAT_MINI_SECRET);
        }
        logger.info("微信授权码：" + code + ",当前用户id：" + sessionUserId + "--type--" + type + ",session.id:" + session.getId());
        Map<String, Object> wto = wechatUtils.getWechatTokenAndOpenId(appid, secret, code, type);
        if (wto == null || wto.get("openid") == null) {
            return R.error(ResultCodeEnum.ERROR.getCode(), "获取微信用户openid失败");
        }
        String openId = (String) wto.get("openid");
        if (sessionUserId != null) {
            // 关联用户openid
            StoreUser user = storeUserService.selectByPrimaryKey(sessionUserId);
            if (user != null) {
                if (Constants.MINI == type) {
                    user.setMiniOpenId(openId);
                    logger.info("当前用户：" + getUserName(), "小程序openid更新完成：" + openId);
                } else if (Constants.PBULIC == type) {
                    user.setWxOpenId(openId);
                    logger.info("当前用户：" + getUserName(), "公众号openid更新完成：" + openId);
                }
                storeUserService.updateByPrimaryKeySelective(user);
            }
        }
        return R.ok().put("openId", openId);
    }

}
