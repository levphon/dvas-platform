package com.glsx.vasp.admin.modules.report.mapper;

import com.glsx.vasp.admin.modules.report.model.BaseReportModel;
import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/12 17:30
 * @since 1.0.0
 */
public interface ReportMapper extends BaseMapper<BaseReportModel> {

    @DataPermAspect(showSql = true, tablePerix = "ds")
    int selectStoreOrderTotalCnt();

    @DataPermAspect(showSql = true, tablePerix = "dg")
    List<BaseReportModel> selectGroupByGoods();

    @DataPermAspect(showSql = true, tablePerix = "ds")
    List<BaseReportModel> selectGroupByStore(Map<String, Object> params);

}
