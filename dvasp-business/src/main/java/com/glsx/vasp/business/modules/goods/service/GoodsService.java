package com.glsx.vasp.business.modules.goods.service;

import com.glsx.vasp.business.modules.goods.entity.Goods;
import com.glsx.vasp.business.modules.goods.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    public Goods getById(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    public List<Goods> search(Map<String, Object> params) {
        return goodsMapper.search(params);
    }
}
