package com.glsx.vasp.business.modules.user.service;

import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.modules.user.mapper.StoreUserMapper;
import com.glsx.vasp.business.modules.user.entity.StoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author qurong
 * @Title UserServiceImpl.java
 * @Package com.qurong.service.impl
 * @Description 用户信息
 * @date 2018年10月24日 下午2:37:40
 */
@Service
public class StoreUserService {

    @Autowired
    private StoreUserMapper userMapper;

    public StoreUser getUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    public int saveOrUpdate(StoreUser u) {
        if (u.getId() != null){
            userMapper.updateByPrimaryKeySelective(u);
        } else{
            u.setCreateTime(new Date());
            u.setDelFlag(Constants.NOT_DEL);
            u.setEnableStatus(Constants.ENABLE_STATUS_CHECK);
            userMapper.insertUseGeneratedKeys(u);
        }
        return u.getId();
    }

    public StoreUser getUserByMiniOpenId(String miniOpenId) {
        return userMapper.selectUserByMiniOpenId(miniOpenId);
    }

    public int updateMiniOpenIdByPrimaryKey(StoreUser u) {
        return userMapper.updateMiniOpenIdByPrimaryKey(u);
    }


    public StoreUser selectByPrimaryKey(Integer id) {

        return userMapper.selectByPrimaryKey(id);
    }

    public void updateByPrimaryKeySelective(StoreUser user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
