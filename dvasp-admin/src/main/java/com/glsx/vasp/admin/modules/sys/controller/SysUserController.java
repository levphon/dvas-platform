package com.glsx.vasp.admin.modules.sys.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.glsx.vasp.admin.modules.CommonController;
import com.glsx.vasp.admin.modules.sys.entity.SysUser;
import com.glsx.vasp.admin.modules.sys.service.SysUserRoleService;
import com.glsx.vasp.admin.modules.sys.service.SysUserService;
import com.glsx.vasp.annotation.SysLog;
import com.glsx.vasp.validator.ValidatorUtils;
import com.glsx.vasp.validator.group.AddGroup;
import com.glsx.vasp.validator.group.UpdateGroup;
import com.glsx.vasp.web.Pagination;
import com.glsx.vasp.web.R;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统用户
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends CommonController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 用户查询
     */
    @GetMapping("/search")
    public R list(@RequestParam Map<String, Object> params, Pagination pagination) {
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        List<SysUser> pages = sysUserService.search(params);
        long total = ((Page<SysUser>) pages).getTotal();
        return R.ok().putPageData(pages, total);
    }

    /**
     * 用户列表
     */
    @GetMapping("/list")
    public R list() {
        List<SysUser> list = sysUserService.list();
        return R.ok().put("list", list);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public R info() {
        return R.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @RequestMapping("/password")
    public R password(String password, String newPassword) {

        //原密码
//        password = ShiroUtils.sha256(password, getUser().getSalt());
        //新密码
//        newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());

        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return R.error("原密码不正确");
        }
        return R.ok();
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    public R info(@PathVariable("userId") Integer userId) {
        SysUser user = sysUserService.getById(userId);
        //获取用户所属的角色列表
        Set<String> roleIdSet = sysUserRoleService.getRoleIdListByUserId(userId);
        user.setRoleIdList(roleIdSet);
        return R.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    public R save(@ModelAttribute SysUser user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);

        sysUserService.save(user);
        return R.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    public R update(@ModelAttribute SysUser user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        sysUserService.update(user);
        return R.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    public R delete(@ModelAttribute Integer[] userIds) {
        if (ArrayUtils.contains(userIds, 1)) {
            return R.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("当前用户不能删除");
        }

        sysUserService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }
}
