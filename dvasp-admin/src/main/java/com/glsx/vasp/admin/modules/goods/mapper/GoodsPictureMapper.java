package com.glsx.vasp.admin.modules.goods.mapper;

import com.glsx.vasp.admin.modules.goods.entity.GoodsPicture;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsPictureMapper extends BaseMapper<GoodsPicture> {

    List<GoodsPicture> selectGoodsPicturesByGoodsId(@Param("goodsId") Integer goodsId);

    int logicDeleteByGoodsId(Integer id);

}