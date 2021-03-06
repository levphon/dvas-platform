package com.glsx.vasp.business.modules.areas.controller;

import com.glsx.vasp.business.common.annotation.AuthCheck;
import com.glsx.vasp.business.common.exception.ResultCodeEnum;
import com.glsx.vasp.business.modules.areas.service.BaseAreasService;
import com.glsx.vasp.entity.SysArea;
import com.glsx.vasp.web.R;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author liumh
 * @date 2019-04-03 11:33:49
 */
@RestController
@RequestMapping("/sysAreas")
public class BaseAreasController {
    @Autowired
    private BaseAreasService baseAreasService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //PageUtils page = baseAreasService.queryPage(params);

        return R.ok().put("page", "");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        //BaseAreasEntity baseAreas = baseAreasService.getById(id);

        return R.ok().put("baseAreas", "");
    }

    @AuthCheck
    @RequestMapping("/seacherByCityCode")
    public R searchByCityCode(@NotBlank(message = "城市编码不能为空")String cityCode) {
        Map<String, String> params = new HashMap<>();
        params.put("cityCode", cityCode);
        List<SysArea> list = baseAreasService.searchByCityCode(params);
        if (CollectionUtils.isEmpty(list))
            R.error(ResultCodeEnum.AREAS_NOT_EXIST.getCode(), ResultCodeEnum.AREAS_NOT_EXIST.getMsg());
        return R.ok().put("list", list);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SysArea baseAreas) {
        //baseAreasService.save(baseAreas);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SysArea baseAreas) {
        //ValidatorUtils.validateEntity(baseAreas);
        //baseAreasService.updateById(baseAreas);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        //baseAreasService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
