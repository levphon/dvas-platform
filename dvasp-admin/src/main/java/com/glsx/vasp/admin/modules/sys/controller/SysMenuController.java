package com.glsx.vasp.admin.modules.sys.controller;

import com.glsx.vasp.admin.modules.CommonController;
import com.glsx.vasp.admin.modules.Constants;
import com.glsx.vasp.admin.modules.sys.entity.SysResource;
import com.glsx.vasp.admin.modules.sys.model.SysMenuTreeModel;
import com.glsx.vasp.admin.modules.sys.service.SysResourceService;
import com.glsx.vasp.annotation.SysLog;
import com.glsx.vasp.exception.ValidateException;
import com.glsx.vasp.model.TreeModel;
import com.glsx.vasp.utils.TreeModelUtil;
import com.glsx.vasp.web.R;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 系统菜单
 *
 * @author liuyf@didihu.com.cn
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends CommonController {

    @Autowired
    private SysResourceService sysResourceService;

    @GetMapping("/tree")
    @ApiOperation("获取菜单树")
    @SysLog(printSpendTime = true, saveLog = false)
    public R tree(@RequestParam Integer rootNo) {
        TreeModel treeModel = null;
        List<SysResource> sysMenuList = sysResourceService.list();
        if (CollectionUtils.isNotEmpty(sysMenuList)) {
            List<SysMenuTreeModel> sysMenuTreeModels = sysMenuList.stream().map(sysResource -> new SysMenuTreeModel(sysResource)).collect(Collectors.toList());
            List<? extends TreeModel> convert = TreeModelUtil.fastConvert(sysMenuTreeModels, rootNo);
            if (CollectionUtils.isNotEmpty(convert)) treeModel = convert.get(0);
        }
        return R.ok().put("menus", treeModel);
    }

    /**
     * 导航菜单
     */
    @GetMapping("/listByUser")
    @ApiOperation("当前用户的所有组织机构")
    @SysLog(printSpendTime = true, saveLog = false)
    public R listByUser() {
        List<SysResource> menuList = sysResourceService.getResourcesByUserId(getUserId());
        return R.ok().put("list", menuList);
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    public R list() {
        List<SysResource> menuList = sysResourceService.list();
        for (SysResource sysResource : menuList) {
            SysResource parentMenuEntity = sysResourceService.getById(sysResource.getParentId());
            if (parentMenuEntity != null) {
                sysResource.setParentName(parentMenuEntity.getMenuName());
            }
        }
        return R.ok().put("list", menuList);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/select")
    public R select() {
        //查询列表数据
        List<SysResource> menuList = sysResourceService.queryNotButtonList();

        //添加顶级菜单
        SysResource root = new SysResource();
        root.setMenuId(0);
        root.setMenuName("一级菜单");
        root.setParentId(-1);
        //root.setOpen(true);
        menuList.add(root);

        return R.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    public R info(@PathVariable("menuId") Integer menuId) {
        SysResource menu = sysResourceService.getById(menuId);
        return R.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @SysLog("保存菜单")
    @RequestMapping("/save")
    public R save(@ModelAttribute SysResource menu) {
        //数据校验
        verifyForm(menu);

        sysResourceService.save(menu);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改菜单")
    @RequestMapping("/update")
    public R update(@ModelAttribute SysResource menu) {
        //数据校验
        verifyForm(menu);

        sysResourceService.updateById(menu);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除菜单")
    @RequestMapping("/delete")
    public R delete(Integer menuId) {
        if (menuId <= 31) {
            return R.error("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SysResource> menuList = sysResourceService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return R.error("请先删除子菜单或按钮");
        }

        sysResourceService.delete(menuId);

        return R.ok();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysResource menu) {
        if (StringUtils.isBlank(menu.getMenuName())) {
            throw new ValidateException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new ValidateException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType().equals(Constants.MenuType.MENU.getValue())) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new ValidateException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        String parentType = Constants.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysResource parentMenu = sysResourceService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (Constants.MenuType.CATALOG.getValue().equals(menu.getType()) || Constants.MenuType.MENU.getValue().equals(menu.getType())) {
            if (!parentType.equals(Constants.MenuType.CATALOG.getValue())) {
                throw new ValidateException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (Objects.equals(menu.getType(), Constants.MenuType.BUTTON.getValue())) {
            if (parentType != Constants.MenuType.MENU.getValue()) {
                throw new ValidateException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
