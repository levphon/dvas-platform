package com.glsx.vasp.admin.modules.sys.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.glsx.vasp.admin.modules.CommonController;
import com.glsx.vasp.admin.modules.sys.entity.SysRole;
import com.glsx.vasp.admin.modules.sys.service.SysRoleResourceService;
import com.glsx.vasp.admin.modules.sys.service.SysRoleService;
import com.glsx.vasp.annotation.SysLog;
import com.glsx.vasp.validator.ValidatorUtils;
import com.glsx.vasp.validator.group.AddGroup;
import com.glsx.vasp.validator.group.UpdateGroup;
import com.glsx.vasp.web.Pagination;
import com.glsx.vasp.web.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author liuyf@didihu.com.cn
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends CommonController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleResourceService sysRoleMenuService;

    /**
     * 角色查询
     */
    @RequestMapping("/search")
    public R list(@RequestParam Map<String, Object> params, Pagination pagination) {
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        List<SysRole> pages = sysRoleService.search(params);
        long total = ((Page<SysRole>) pages).getTotal();
        return R.ok().putPageData(pages, total);
    }

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    public R list() {
        List<SysRole> list = sysRoleService.list();
        return R.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    public R info(@PathVariable("roleId") Integer roleId) {
        SysRole role = sysRoleService.getById(roleId);

        //查询角色对应的菜单
        List<Integer> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        //查询角色对应的机构

        return R.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @SysLog("保存角色")
    @RequestMapping("/save")
    public R save(@ModelAttribute SysRole role) {
        ValidatorUtils.validateEntity(role, AddGroup.class);

        sysRoleService.saveRole(role);

        return R.ok();
    }

    /**
     * 修改角色
     */
    @SysLog("修改角色")
    @RequestMapping("/update")
    public R update(@ModelAttribute SysRole role) {
        ValidatorUtils.validateEntity(role, UpdateGroup.class);
        sysRoleService.update(role);
        return R.ok();
    }

    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @RequestMapping("/delete")
    public R delete(@ModelAttribute Integer[] roleIds) {
        sysRoleService.deleteBatch(roleIds);
        return R.ok();
    }
}
