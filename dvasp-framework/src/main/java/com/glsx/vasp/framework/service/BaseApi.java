package com.glsx.vasp.framework.service;

import com.glsx.vasp.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseApi<T extends BaseEntity> {

    /**
     * 查询
     *
     * @param id
     * @return
     */
    T get(String id);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    T find(String id);

    /**
     * 创建
     *
     * @param entity
     * @return
     */
    T create(T entity);

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(String id);

    int delete(T entity);

    /**
     * 读取所有
     *
     * @param pageable
     * @return
     */
    Page<T> page(Pageable pageable);

    /**
     * 判断id是否存在
     *
     * @param id
     * @return
     */
    boolean exists(String id);

}