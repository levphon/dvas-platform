package com.glsx.vasp.admin.modules.home.controller;

import com.glsx.vasp.admin.common.utils.wechat.WechatUtils;
import com.glsx.vasp.admin.modules.CommonController;
import com.glsx.vasp.admin.modules.sys.entity.SysOrganization;
import com.glsx.vasp.admin.modules.sys.service.SysOrganizationService;
import com.glsx.vasp.annotation.SysLog;
import com.glsx.vasp.enums.MimeType;
import com.glsx.vasp.framework.components.PropertiesUtils;
import com.glsx.vasp.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 首页
 *
 * @author payu
 * @create 2019/4/12 13:13
 * @since 1.0.0
 */
@RestController
@RequestMapping("/home")
public class HomeController extends CommonController {

    @Autowired
    private SysOrganizationService sysOrganizationService;

    @Autowired
    private WechatUtils wechatUtils;

    /**
     * 生成sp二维码
     */
    @SysLog("生成sp二维码")
    @RequestMapping("/buildSpQRCode")
    public void buildSpQRCode(HttpServletResponse response) throws Exception {
        SysOrganization sysOrg = sysOrganizationService.selectByOrgId(getOrgId());

        String appid = PropertiesUtils.getProperty("wechat.business.mini.appid");
        String appSecret = PropertiesUtils.getProperty("wechat.business.mini.appSecret");
        String wechatToken = wechatUtils.getWechatToken(appid, appSecret);

        StringBuilder codeText = new StringBuilder();
        String orgName = "";
        if (sysOrg != null) {
            orgName = sysOrg.getName();
            codeText.append(sysOrg.getOrgId());
        }
        orgName = orgName + "_" + DateUtils.format(new Date());
        logger.info("二维码内容：" + codeText.toString());
        response.setContentType(MimeType.PNG.getContentType());
        String encodeFileName = encodeFilename(orgName) + "." + MimeType.PNG.getSuffix();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + encodeFileName + "\"");
        wechatUtils.getMiniQrCode(codeText.toString(), wechatToken, response.getOutputStream());

//        try {
//            QRCodeUtils.encode(codeText.toString(), response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
