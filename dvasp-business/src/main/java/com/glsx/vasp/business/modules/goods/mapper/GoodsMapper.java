package com.glsx.vasp.business.modules.goods.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.business.modules.goods.entity.Goods;
import com.glsx.vasp.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 商品表
 *
 * @author liumh
 * @date 2019-04-01 17:52:54
 */
@Component
public interface GoodsMapper extends BaseMapper<Goods> {

    @DataPermAspect(showSql = true, tablePerix = "dg")
    List<Goods> search(Map<String, Object> params);
}
