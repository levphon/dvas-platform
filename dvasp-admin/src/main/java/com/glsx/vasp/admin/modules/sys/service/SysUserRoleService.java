package com.glsx.vasp.admin.modules.sys.service;

import com.glsx.vasp.admin.modules.sys.mapper.SysUserMapper;
import com.glsx.vasp.admin.modules.sys.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 3/19/2019 15:09
 * @since 1.0.0
 */
@Service
public class SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    public Set<String> getRoleIdListByUserId(Integer userId) {
        return sysUserRoleMapper.selectRoleIdListByUserId(userId);
    }
}
