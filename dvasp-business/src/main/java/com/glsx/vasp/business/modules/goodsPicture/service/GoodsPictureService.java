package com.glsx.vasp.business.modules.goodsPicture.service;

import com.glsx.vasp.business.modules.goodsPicture.entity.GoodsPicture;
import com.glsx.vasp.business.modules.goodsPicture.mapper.GoodsPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class GoodsPictureService {

    @Autowired
    GoodsPictureMapper goodsPictureMapper;

    public List<GoodsPicture> search(Map<String, Object> params) {
        return goodsPictureMapper.search(params);
    }

}
