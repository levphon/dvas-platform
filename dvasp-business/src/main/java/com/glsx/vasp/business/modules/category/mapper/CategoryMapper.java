package com.glsx.vasp.business.modules.category.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.business.modules.category.entity.Category;
import com.glsx.vasp.business.modules.store.entity.Store;
import com.glsx.vasp.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 商品分类表
 * 
 * @author Mark
 * @date 2019-04-01 15:17:15
 */
@Component
public interface CategoryMapper extends BaseMapper<Category> {

    @DataPermAspect(showSql = true, tablePerix = "dc")
    List<Category> search(Map<String, Object> params);
}
