package com.glsx.vasp.admin.modules.sys.service;

import com.glsx.vasp.admin.common.exception.AdminException;
import com.glsx.vasp.admin.common.utils.ShiroUtils;
import com.glsx.vasp.admin.common.utils.UserUtil;
import com.glsx.vasp.admin.modules.sys.entity.SysUser;
import com.glsx.vasp.admin.modules.sys.mapper.SysUserMapper;
import com.glsx.vasp.admin.modules.sys.model.SysUserUpdatePwdModel;
import com.glsx.vasp.enums.SysContants;
import com.glsx.vasp.exception.SystemMessage;
import com.glsx.vasp.utils.encryption.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysUserService {

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据账号名获取系统用户
     *
     * @param account
     * @return
     */
    public SysUser getByAccount(String account) {
        //去商品平台查询用户
        SysUser merchantStaff = userUtil.getUserByName(account);
        if (merchantStaff == null) return merchantStaff;

        //去本系统数据库查询用户
//        SysUser localUser = sysUserMapper.selectByAccount(account);
//        if (localUser == null) return localUser;
//        merchantStaff.setId(localUser.getId());

        return merchantStaff;
    }

    /**
     * 查询用户列表
     *
     * @param sysUser
     * @param orgName
     * @return
     */
    public List<SysUser> search(Map<String, Object> params) {
        List<SysUser> sysUsers = sysUserMapper.search(params);
        //todo
        return sysUsers;
    }

    public List<SysUser> list() {
        return sysUserMapper.selectAll();
    }

    public void save(SysUser sysUser) {
        SysUser user = sysUserMapper.selectByAccount(sysUser.getAccount());
        if (user != null) {
            throw SystemMessage.SYSUSER_LOGINGNAME_ExIST.exception();
        }

        SysUser loginUser = ShiroUtils.currentUser();
//        sysUser.setPassword(EncryptUtils.md5Encrypt(sysUser.getPassword(), Constants.SECRET));
        sysUser.setPassword(EncryptUtils.PBEEncrypt(sysUser.getPassword()));
        sysUser.setCreateUser(loginUser.getId());
        sysUser.setCreateTime(new Date());
        sysUserMapper.insert(sysUser);
    }

    /**
     * 修改信息
     *
     * @param sysUser
     */
    public void updateUser(SysUser sysUser) {
        Integer userId = sysUser.getId();
        //帐号组织机构不允许修改
        SysUser dbUser = sysUserMapper.selectByPrimaryKey(userId);
//        if(!dbUser.getOrgNo().equals(sysUser.getOrgNo())){
//            throw AdminException.create("帐号组织机构不允许修改");
//        }
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * 修改密码
     *
     * @param sysUserUpdatePwdModel
     */
    public void updateUserPassword(SysUserUpdatePwdModel sysUserUpdatePwdModel) {
        SysUser sessionSysUser = ShiroUtils.currentUser();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(sessionSysUser.getId());
        String newPassword = sysUserUpdatePwdModel.getNewPassword();
        String oldPassword = sysUserUpdatePwdModel.getOldPassword();
        //获取旧密码加密串
//        String oldPasswordEncrypt = EncryptUtils.md5Encrypt(oldPassword, Constants.SECRET);
        String oldPasswordEncrypt = EncryptUtils.PBEEncrypt(oldPassword);
        if (!oldPasswordEncrypt.equals(sysUser.getPassword())) {
            throw AdminException.create("旧密码不匹配");
        }
//        sysUser.setPassword(EncryptUtils.md5Encrypt(newPassword, Constants.SECRET));
        sysUser.setPassword(EncryptUtils.PBEEncrypt(newPassword));
        sysUserMapper.updateByPrimaryKey(sysUser);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    public int deleteUser(Integer id) {
        SysUser sessionSysUser = ShiroUtils.currentUser();
        if (id.equals(sessionSysUser.getId())) {
            throw AdminException.create("禁止删除登录账户本身!");
        }
        SysUser user = sysUserMapper.selectByPrimaryKey(id);
        user.setDelFlag(SysContants.DeleteStatus.delete.getCode());
        return sysUserMapper.updateByPrimaryKey(user);
    }

    /**
     * 切换用户启用停用状态
     *
     * @param userId
     * @param status
     */
    public void switchUserStatus(Integer userId, Integer status) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        sysUser.setEnableStatus(status);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * 重置密码
     *
     * @param userId
     */
    public void resetUserPwd(Integer userId) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
//        sysUser.setPassword(EncryptUtils.md5Encrypt("123456", Constants.SECRET));
        sysUser.setPassword(EncryptUtils.PBEEncrypt("6543210"));
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * 用户详情查询
     *
     * @param userId
     * @return
     */
    public SysUser queryUserDetail(Integer userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    public SysUser getByLoginName(String loginName) {
        return sysUserMapper.selectByAccount(loginName);
    }

    public boolean updatePassword(Integer userId, String password, String newPassword) {
        return false;
    }


    public void update(SysUser user) {

    }

    public void removeByIds(List<Integer> userIds) {

    }

    public SysUser getById(Integer userId) {
        return null;
    }


}
