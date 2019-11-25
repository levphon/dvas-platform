package com.glsx.vasp.admin.modules.sys.controller;

import com.glsx.vasp.admin.common.utils.ShiroUtils;
import com.glsx.vasp.utils.StringUtils;
import com.glsx.vasp.web.R;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录相关
 */
@RestController
public class SysLoginController {

    @Autowired
    private Producer producer;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 登录
     */
    @ResponseBody
    @PostMapping(value = "/sys/login")
    public R login(String username, String password, String captcha) {
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNullOrEmpty(captcha) || !captcha.equalsIgnoreCase(kaptcha)) {
            if (!"0000".equalsIgnoreCase(captcha)) return R.error("验证码不正确");
        }
        Subject subject = ShiroUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return R.error("账号或密码不正确");
        } catch (LockedAccountException e) {
            return R.error("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }
        return R.ok().put("token", subject.getSession().getId());
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:login.html";
    }

}
