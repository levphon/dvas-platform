package com.glsx.vasp.admin.modules.sys.service;

import com.glsx.vasp.admin.modules.sys.entity.SysResource;
import com.glsx.vasp.admin.modules.sys.mapper.SysResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 3/19/2019 17:05
 * @since 1.0.0
 */
@Service
public class SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    public List<SysResource> list() {
        return sysResourceMapper.selectAll();
    }

    public List<SysResource> getResourcesByUserId(Integer userId) {
        return sysResourceMapper.selectResourcesByUserId(userId);
    }

    public void save(SysResource menu) {

    }

    public SysResource getById(Integer menuId) {
        return null;
    }

    public void updateById(SysResource menu) {

    }

    public List<SysResource> queryListParentId(Integer menuId) {
        return null;
    }

    public void delete(Integer menuId) {

    }

    public List<SysResource> queryNotButtonList() {
        return null;
    }
}
