package com.glsx.vasp.admin.modules.order.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.glsx.vasp.admin.modules.CommonController;
import com.glsx.vasp.admin.modules.order.entity.StoreOrder;
import com.glsx.vasp.admin.modules.order.model.StoreOrderExportModel;
import com.glsx.vasp.admin.modules.order.service.StoreOrderService;
import com.glsx.vasp.enums.MimeType;
import com.glsx.vasp.utils.DateUtils;
import com.glsx.vasp.web.Pagination;
import com.glsx.vasp.web.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 门店订单
 *
 * @author payu
 * @create 2019/4/3 18:58
 * @since 1.0.0
 */
@RestController
@RequestMapping("/storeorder")
public class StoreOrderController extends CommonController {

    @Autowired
    private StoreOrderService storeOrderService;

    /**
     * 门店订单查询
     */
    @RequestMapping("/search")
    public R list(@RequestParam Map<String, Object> params, Pagination pagination) {
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        List<StoreOrder> pages = storeOrderService.search(params);
        long total = ((Page<StoreOrder>) pages).getTotal();
        return R.ok().putPageData(pages, total);
    }

    /**
     * 门店订单导出
     */
    @RequestMapping("/export")
    public void export(HttpServletResponse response, @RequestParam Map<String, Object> params) throws IOException {
        OutputStream out = response.getOutputStream();
        String fileName = "订单列表" + DateUtils.format(new Date());
        response.setContentType(MimeType.EXCEL2007.getContentType());
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        String encodeFileName = encodeFilename(fileName) + "." + MimeType.EXCEL2007.getSuffix();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + encodeFileName + "\"");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, StoreOrderExportModel.class);
        List<StoreOrderExportModel> data = storeOrderService.export(params);
        writer.write(data, sheet1);
        writer.finish();
    }

    /**
     * 订单详情信息
     */
    @GetMapping("/info/{orderId}")
    public R info(@PathVariable("orderId") Integer orderId) {
        StoreOrder order = storeOrderService.getStoreOrderInfo(orderId);
        return R.ok().put("order", order);
    }

    /**
     * 商品信息
     */
    @GetMapping("/listgoods/{orderId}")
    public R goodsInfo(@PathVariable("orderId") Integer orderId) {
        StoreOrder order = storeOrderService.getStoreOrderInfo(orderId);
        return R.ok().put("goodsList", order.getGoodsList());
    }

}
