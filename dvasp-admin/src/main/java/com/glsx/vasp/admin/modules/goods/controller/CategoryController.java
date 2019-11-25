package com.glsx.vasp.admin.modules.goods.controller;

import com.glsx.vasp.admin.modules.CommonController;
import com.glsx.vasp.admin.modules.goods.entity.Category;
import com.glsx.vasp.admin.modules.goods.service.CategoryService;
import com.glsx.vasp.web.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 商品/服务类别
 *
 * @author payu
 * @create 3/29/2019 12:49
 * @since 1.0.0
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends CommonController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 商品分类列表
     */
    @RequestMapping("/list")
    public R list() {
        List<Category> list = categoryService.list();
        return R.ok().put("list", list);
    }

    /**
     * 商品分类列表by父级code
     */
    @RequestMapping("/listSubCategories/{pcode}")
    public R list(@PathVariable("pcode") String parentCode) {
        List<Category> list = categoryService.listByParentCode(parentCode);
        return R.ok().put("list", list);
    }

}
