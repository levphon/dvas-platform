package com.glsx.vasp.admin.common.config.shiro;

import com.glsx.vasp.admin.modules.Constants;
import com.glsx.vasp.admin.modules.sys.entity.SysUser;
import com.glsx.vasp.admin.modules.sys.service.SysOrganizationService;
import com.glsx.vasp.admin.modules.sys.service.SysUserRoleService;
import com.glsx.vasp.admin.modules.sys.service.SysUserService;
import com.glsx.vasp.enums.SysContants;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 后台用户 Realm
 */
public class SysUserRealm extends AuthorizingRealm {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysOrganizationService sysOrganizationService;

    /**
     * 用户认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());//明文密码

        SysUser principal = sysUserService.getByAccount(username);
        if (principal == null)
            throw new UnknownAccountException("帐号或密码错误:" + username);

        if (SysContants.EnableStatus.disable.getCode().equals(principal.getEnableStatus()))
            throw new DisabledAccountException("帐号被禁用:" + username);

        String realmName = getName();
        String credentials = principal.getPassword();
        ByteSource credentialsSalt = ByteSource.Util.bytes(Constants.SALT_SOURCE);

//        String encryptPassword = EncryptUtils.md5Encrypt(password, Constants.SECRET);
//        String encryptPassword = EncryptUtils.PBEEncrypt(password);
//        if (!credentials.equals(encryptPassword))
//            throw new UnknownAccountException("帐号或密码错误");

        //清空密码
        principal.setPassword("");
        return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //角色列表
        SysUser loginUser = (SysUser) principalCollection.getPrimaryPrincipal();
        Set<String> roles = sysUserRoleService.getRoleIdListByUserId(loginUser.getId());
        authorizationInfo.setRoles(roles);

        //以组织机构
//        SysOrganization sysOrganization = organizationService.selectByOrgId(loginUser.getOrgId());
//        Integer orgLevel = sysOrganization.getOrgLevel();
//        HashSet<String> orgLevelSet = new HashSet<String>(1);
//        orgLevelSet.add(orgLevel + "");
//        authorizationInfo.setStringPermissions(orgLevelSet);
        return authorizationInfo;
    }

}
