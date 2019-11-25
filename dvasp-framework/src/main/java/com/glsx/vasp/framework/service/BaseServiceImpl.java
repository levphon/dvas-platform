package com.glsx.vasp.framework.service;

import com.glsx.vasp.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BaseServiceImpl<T extends BaseEntity> implements BaseApi<T> {

//    @Autowired
//    private JpaRepository<T, String> baseRepository;

    @Override
    public T get(String id) {
        return null;
    }

    @Override
    public T find(String id) {
        return null;
    }

    @Override
    public T create(T entity) {
        return null;
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int delete(T entity) {
        return 0;
    }

    @Override
    public Page<T> page(Pageable pageable) {
        return null;
    }

    @Override
    public boolean exists(String id) {
        return false;
    }
}
