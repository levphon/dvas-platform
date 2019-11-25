package com.glsx.vasp.business.modules.shopcar.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.business.modules.shopcar.entity.Shopcar;
import com.glsx.vasp.business.modules.shopcar.entity.ShopcarDetail;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 购物车表
 * 
 * @author liumh
 * @date 2019-04-02 16:00:37
 */
@Component
public interface ShopcarMapper extends BaseMapper<Shopcar> {

    @DataPermAspect(showSql = true, tablePerix = "")
    List<ShopcarDetail> searchShopcarDetail(Map<String,Object> params);

    @DataPermAspect(showSql = true, tablePerix = "ds")
    List<Shopcar> search(Map<String,Object> params);
}
