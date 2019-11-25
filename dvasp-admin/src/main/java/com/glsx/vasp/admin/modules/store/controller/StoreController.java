package com.glsx.vasp.admin.modules.store.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.glsx.vasp.admin.modules.CommonController;
import com.glsx.vasp.admin.modules.store.entity.Store;
import com.glsx.vasp.admin.modules.store.model.StoreExportModel;
import com.glsx.vasp.admin.modules.store.model.StoreRegionVO;
import com.glsx.vasp.admin.modules.store.service.StoreService;
import com.glsx.vasp.annotation.SysLog;
import com.glsx.vasp.enums.MimeType;
import com.glsx.vasp.enums.SysContants;
import com.glsx.vasp.utils.DateUtils;
import com.glsx.vasp.validator.ValidatorUtils;
import com.glsx.vasp.validator.group.AddGroup;
import com.glsx.vasp.validator.group.UpdateGroup;
import com.glsx.vasp.web.Pagination;
import com.glsx.vasp.web.R;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 3/26/2019 15:10
 * @since 1.0.0
 */
@RestController
@RequestMapping("/store")
public class StoreController extends CommonController {

    @Autowired
    private StoreService storeService;

    /**
     * 门店查询
     */
    @RequestMapping("/search")
    public R list(@RequestParam Map<String, Object> params, Pagination pagination) {
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        List<Store> pages = storeService.search(params);
        long total = ((Page<Store>) pages).getTotal();
        return R.ok().putPageData(pages, total);
    }

    /**
     * 门店导出
     */
    @RequestMapping("/export")
    public void export(HttpServletResponse response, @RequestParam Map<String, Object> params) throws IOException {
        OutputStream out = response.getOutputStream();
        String fileName = "门店列表" + DateUtils.format(new Date());
        response.setContentType(MimeType.EXCEL2007.getContentType());
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        String encodeFileName = encodeFilename(fileName) + "." + MimeType.EXCEL2007.getSuffix();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + encodeFileName + "\"");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, StoreExportModel.class);
        List<StoreExportModel> data = storeService.export(params);
        writer.write(data, sheet1);
        writer.finish();
    }

    /**
     * 门店列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        List<StoreRegionVO> list = storeService.listByName(params);
        return R.ok().put("list", list);
    }

    @GetMapping("/tree")
    @ApiOperation("获取门店树")
    @SysLog(printSpendTime = true, saveLog = false)
    public R tree(@RequestParam Map<String, Object> params) {
        List<StoreRegionVO> list = storeService.listByName(params);
        Map<String, List<StoreRegionVO>> map = new TreeMap<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (StoreRegionVO vo : list) {
                if (map.containsKey(vo.getRegionName())) {
                    List<StoreRegionVO> subList = map.get(vo.getRegionName());
                    subList.add(vo);
                } else {
                    List<StoreRegionVO> subList = new ArrayList<>();
                    subList.add(vo);
                    map.put(vo.getRegionName(), subList);
                }
            }
        }
        return R.ok().put("stores", map);
    }

    /**
     * 门店信息
     */
    @GetMapping("/info/{storeId}")
    public R info(@PathVariable("storeId") Integer storeId) {
        Store store = storeService.getById(storeId);
        return R.ok().put("store", store);
    }

    /**
     * 保存门店
     */
    @SysLog("保存门店")
    @RequestMapping("/save")
    public R save(@ModelAttribute Store store) {
        ValidatorUtils.validateEntity(store, AddGroup.class);
        Store store1 = storeService.getByName(store.getName());
        if (store1 != null) return R.error("该门店已存在！");

        store.setOrgId(getOrgId());
        store.setEnableStatus(SysContants.EnableStatus.enable.getCode());
        store.setCreateUser(getUserId());
        store.setCreateTime(new Date());
        storeService.save(store);
        return R.ok();
    }

    /**
     * 修改门店
     */
    @SysLog("修改门店")
    @RequestMapping("/update")
    public R update(@ModelAttribute Store store) {
        ValidatorUtils.validateEntity(store, UpdateGroup.class);
        Store store1 = storeService.getByName(store.getName());
        if (store1 != null && !store1.getId().equals(store.getId())) return R.error("该门店已存在！");

        store.setUpdateUser(getUserId());
        storeService.update(store);
        return R.ok();
    }

    /**
     * 审核门店
     */
    @SysLog("审核通过门店")
    @RequestMapping("/auditing")
    public R auditing(@RequestParam("storeId") Integer storeId) {
        storeService.changeStatusById(storeId, SysContants.EnableStatus.enable.getCode());
        return R.ok();
    }

    /**
     * 启用门店
     */
    @SysLog("启用门店")
    @RequestMapping("/enabled")
    public R enabled(@RequestParam("storeId") Integer storeId) {
        storeService.changeStatusById(storeId, SysContants.EnableStatus.enable.getCode());
        return R.ok();
    }

    /**
     * 停用门店
     */
    @SysLog("停用门店")
    @RequestMapping("/disabled")
    public R disabled(@RequestParam("storeId") Integer storeId) {
        storeService.changeStatusById(storeId, SysContants.EnableStatus.disable.getCode());
        return R.ok();
    }

    /**
     * 删除门店
     */
    @SysLog("删除门店")
    @RequestMapping("/delete")
    public R delete(@RequestParam("storeIds") Integer[] storeIds) {
        storeService.logicDeleteBatch(storeIds);
        return R.ok();
    }

}
