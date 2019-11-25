package com.glsx.vasp.admin.modules.goods.mapper;

import com.glsx.vasp.admin.modules.goods.entity.Category;
import com.glsx.vasp.mapper.BaseMapper;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> listByParentCode(String pCode);

}