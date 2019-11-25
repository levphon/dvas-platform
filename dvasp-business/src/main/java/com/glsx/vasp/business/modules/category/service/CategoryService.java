package com.glsx.vasp.business.modules.category.service;

import com.glsx.vasp.business.modules.category.entity.Category;
import com.glsx.vasp.business.modules.category.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;


    public List<Category> search(Map<String, Object> params) {
        return categoryMapper.search(params);
    }
}
