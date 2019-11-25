package com.glsx.vasp.admin.modules.sys.controller;

import com.glsx.vasp.admin.modules.CommonController;
import com.glsx.vasp.admin.modules.sys.entity.SysOrganization;
import com.glsx.vasp.admin.modules.sys.model.SysOrganizationTreeModel;
import com.glsx.vasp.admin.modules.sys.service.SysOrganizationService;
import com.glsx.vasp.annotation.SysLog;
import com.glsx.vasp.model.TreeModel;
import com.glsx.vasp.utils.TreeModelUtil;
import com.glsx.vasp.web.R;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 3/27/2019 10:04
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sys/org")
public class SysOrgController extends CommonController {

    @Autowired
    private SysOrganizationService sysOrganizationService;

    @GetMapping("/userOrgs")
    @ApiOperation("当前用户的所有组织机构")
    @SysLog(printSpendTime = true, saveLog = false)
    public R userOrgs() {
        Integer orgId = getUser().getOrgId();
        TreeModel treeModel = null;
        List<SysOrganization> sysOrgList = sysOrganizationService.selectChildrenOrgsSelf(orgId);
        if (CollectionUtils.isNotEmpty(sysOrgList)) {
            List<SysOrganizationTreeModel> sysOrganizationTreeModels = sysOrgList.stream().map(sysOrganization -> new SysOrganizationTreeModel(sysOrganization)).collect(Collectors.toList());
            List<? extends TreeModel> convert = TreeModelUtil.fastConvert(sysOrganizationTreeModels, orgId);
            if (CollectionUtils.isNotEmpty(convert)) treeModel = convert.get(0);
        }
        return R.ok().data(treeModel);
    }

    /**
     * 机构列表
     */
    @GetMapping("/list")
    public R select() {
        List<SysOrganization> list = sysOrganizationService.list();
        return R.ok().put("list", list);
    }

}
