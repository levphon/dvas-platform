package com.glsx.vasp.admin.modules.goods.mapper;

import com.glsx.vasp.admin.modules.goods.entity.Goods;
import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface GoodsMapper extends BaseMapper<Goods> {

    @DataPermAspect(showSql = true, tablePerix = "dg")
    List<Goods> selectByCategory(String code);

    @DataPermAspect(showSql = true, tablePerix = "dg")
    List<Goods> search(Map<String, Object> params);

    @DataPermAspect(showSql = true, tablePerix = "dg")
    Goods selectByName(String name);

}