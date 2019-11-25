package com.glsx.vasp.admin.modules.report.service;

import com.glsx.vasp.admin.modules.report.mapper.ReportMapper;
import com.glsx.vasp.admin.modules.report.model.BaseReportModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/12 13:55
 * @since 1.0.0
 */
@Service
public class ReportService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReportMapper reportMapper;

    public int getStoreOrderTotalCnt() {
        return reportMapper.selectStoreOrderTotalCnt();
    }

    public List<BaseReportModel> selectGroupByGoods() {
        return reportMapper.selectGroupByGoods();
    }

    public Map<String, Object> searchGroupByStoreName(Map<String, Object> params) {
        List<BaseReportModel> brmList = reportMapper.selectGroupByStore(params);
        String[] names = new String[brmList.size()];
        for (int i = 0; i < brmList.size(); i++) {
            names[i] = brmList.get(i).getName();
        }
        Map<String, Object> optionData = new TreeMap<>();
        optionData.put("legendData", names);
        optionData.put("seriesData", brmList);
        return optionData;
    }

}
