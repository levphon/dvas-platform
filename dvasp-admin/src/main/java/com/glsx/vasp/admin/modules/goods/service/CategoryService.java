package com.glsx.vasp.admin.modules.goods.service;

import com.glsx.vasp.admin.modules.goods.entity.Category;
import com.glsx.vasp.admin.modules.goods.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 3/29/2019 13:41
 * @since 1.0.0
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> list() {
        return categoryMapper.selectAll();
    }

    public List<Category> listByParentCode(String pCode) {
        return categoryMapper.listByParentCode(pCode);
    }

}
