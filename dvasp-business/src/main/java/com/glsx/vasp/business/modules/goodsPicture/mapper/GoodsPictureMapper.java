package com.glsx.vasp.business.modules.goodsPicture.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.business.modules.goodsPicture.entity.GoodsPicture;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 商品图片表
 * 
 * @author liumh
 * @date 2019-04-02 10:04:45
 */
@Component
public interface GoodsPictureMapper extends BaseMapper<GoodsPicture> {

    @DataPermAspect(showSql = true, tablePerix = "dgp")
    List<GoodsPicture> search(Map<String,Object> params);
}
