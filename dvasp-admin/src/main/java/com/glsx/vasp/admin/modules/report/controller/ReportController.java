package com.glsx.vasp.admin.modules.report.controller;

import com.glsx.vasp.admin.modules.report.model.BaseReportModel;
import com.glsx.vasp.admin.modules.report.service.ReportService;
import com.glsx.vasp.web.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 〈一句话功能简述〉<br>
 * 统计报表
 *
 * @author payu
 * @create 2019/4/12 13:11
 * @since 1.0.0
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 报表查询
     */
    @RequestMapping("/summary")
    public R total() {
        int total = reportService.getStoreOrderTotalCnt();
        List<BaseReportModel> brmList = reportService.selectGroupByGoods();
        return R.ok().put("total", total).put("list", brmList);
    }

    /**
     * 报表查询
     */
    @RequestMapping("/search")
    public R list(@RequestParam Map<String, Object> params) {
        String cType = (String) params.get("cType");
        String rType = (String) params.get("rType");
        Map<String, Object> optionData = new TreeMap<>();
        if ("pie".equals(cType)) {
            optionData = reportService.searchGroupByStoreName(params);
        }
        return R.ok().data(optionData);
    }

}
