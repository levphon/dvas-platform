package com.glsx.vasp.admin.common.utils;

import com.glsx.biz.merchant.common.entity.MerchantSubAccount;
import com.glsx.biz.merchant.service.MerchantStaffService;
import com.glsx.cloudframework.exception.ServiceException;
import com.glsx.vasp.admin.modules.Constants;
import com.glsx.vasp.admin.modules.sys.entity.SysUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author zc
 * @version 1.0
 * @history created by zc 2016年07月14日
 */
@SuppressWarnings("serial")
@Component
public class UserUtil implements Serializable {

    private static Logger logger = Logger.getLogger(UserUtil.class);//日志记录

    @Autowired
    private MerchantStaffService merchantStaffService;

    /**
     * 查询用户
     *
     * @param name 用户名
     * @return
     */
    public SysUser getUserByName(String name) {
        SysUser user = null;
        try {
            MerchantSubAccount account = merchantStaffService.getByLoginName(name);
            if (null != account) {
                user = new SysUser();
                user.setAccountId(account.getId());
                user.setAccount(account.getLoginName());
                user.setOrgId(account.getMerchantId());
                user.setUsername(account.getUserName());
                user.setPhone(account.getMobile());
                user.setPassword(account.getPwd());
                user.setDelFlag(getStatus(account.getDelStatus()));
                user.setCreateTime(account.getCreateTime());
            } else {
                logger.info(name + " from account is null");
            }
        } catch (ServiceException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 查询用户
     *
     * @param id 平台用户id
     * @return
     */
    public SysUser getUserById(int id) {
        SysUser user = null;
        try {
            MerchantSubAccount account = merchantStaffService.findById(id);
            if (null != account) {
                //logger.info("通过AccountId从平台那边获取账号信息：" + account.toString());
                user = new SysUser();
                user.setAccountId(account.getId());
                user.setAccount(account.getLoginName());
                user.setOrgId(account.getMerchantId());
                user.setUsername(account.getUserName());
                user.setPhone(account.getMobile());
                user.setPassword(account.getPwd());
                user.setDelFlag(getStatus(account.getDelStatus()));
                user.setCreateTime(account.getCreateTime());
                //logger.info("getUserById accountId =" + account.getId());
            } else {
                //logger.info(id + " from account is null");
            }
        } catch (ServiceException e) {
            logger.info(e.getMessage());
        }
        return user;
    }

    /**
     * 查询用户列表
     *
     * @param page   分页
     * @param userVo 用户vo
     * @return
     */
//    @SuppressWarnings("unchecked")
//    public Map<String, Object> findAllUserByConditions(Pagination page, UserVo userVo) {
//        List<UserVo> userList = new ArrayList<UserVo>();
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        UserVo vo = null;
//        Integer currentPage = 0;
//        Integer pageSize = null;
//        try {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            if (userVo != null) {
//                map.put("loginName", userVo.getUserName());
//                map.put("userName", userVo.getName());
//                map.put("mobile", userVo.getUserPhone());
//                map.put("orgName", userVo.getOrgName());
//            }
//            map.put("userSource", Constants.USER_SOURCE);
//            if (page == null) {
//                page = new Pagination();//查询全部用户
//            } else {
//                currentPage = page.getCurrentPage();//分页查询
//                pageSize = page.getPerPageSize();
//            }
//            Pagination pagination = merchantStaffService.find(map, currentPage, pageSize);
//            List<HashMap<String, Object>> staffList = (List<HashMap<String, Object>>) pagination.getList();
//            if (CollectionUtils.isNotEmpty(staffList)) {
//                page.setCounts(pagination.getTotalCount());
//                page.setTotalPage(pagination.getTotalPage());
//                for (HashMap<String, Object> staffMap : staffList) {
//                    vo = new UserVo();
//                    vo.setAccountId((Integer) staffMap.get("id"));
//                    vo.setName((String) staffMap.get("userName"));
//                    vo.setCreateTime(DateUtil.formatDate((Date) staffMap.get("createTime")));
//                    vo.setUserName((String) staffMap.get("loginName"));
//                    vo.setUserPhone((String) staffMap.get("mobile"));
//                    vo.setUserPwd((String) staffMap.get("pwd"));
//                    vo.setUserStatus(getStatus((Integer) staffMap.get("delStatus")));
//                    userList.add(vo);
//                }
//                resultMap.put("page", page);
//                resultMap.put("list", userList);
//            } else {
//                logger.info("result is null");
//            }
//        } catch (ServiceException e) {
//            logger.info(e.getMessage());
//        }
//        return resultMap;
//    }

    /**
     * 保存新增用户
     *
     * @param user
     * @return
     */
    public int saveUser(SysUser user) {
        MerchantSubAccount staff = new MerchantSubAccount();
        int accountId = 0;
        try {
            staff.setLoginName(user.getAccount());
            staff.setUserName(user.getUsername());
            staff.setMobile(user.getPhone());
            staff.setPwd(user.getPassword());
            staff.setStatus(Constants.CONFIG_NORMAL);
            staff.setDelStatus(Constants.CONFIG_NORMAL);
            staff.setUserSource(Constants.USER_SOURCE);
            staff.setMerchantId(user.getOrgId());
            staff.setCreateTime(user.getCreateTime());
            accountId = merchantStaffService.create(staff);
            logger.info("create accountId=" + accountId + " username=" + user.getUsername());
            return accountId;
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return accountId;
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    public boolean updateUser(SysUser user) {
        MerchantSubAccount staff = new MerchantSubAccount();
        try {
            staff.setId(user.getAccountId());
            staff.setLoginName(user.getAccount());
            staff.setUserName(user.getUsername());
            staff.setMobile(user.getPhone());
            staff.setPwd(user.getPassword());
            staff.setDelStatus(getStatus(user.getDelFlag()));
            staff.setMerchantId(user.getOrgId());
            staff.setUserSource(Constants.USER_SOURCE);
            merchantStaffService.update(staff);
            logger.info("update accountId=" + staff.getId() + " username=" + user.getUsername());
            return true;
        } catch (ServiceException e) {
            logger.info(e.getMessage());
        }
        return false;
    }


    /**
     * 删除用户
     *
     * @param userId 平台用户id
     * @return
     */
    public boolean deleteUser(int userId) {
        try {
            merchantStaffService.delete(userId);
            logger.info("delete accountId=" + userId);
            return true;
        } catch (ServiceException e) {
            logger.info(e.getMessage());
        }
        return false;
    }

    /**
     * 获取交换两边状态值:平台那边删除状态是： 1使用中,0已删除
     *
     * @param status
     * @return
     */
    public static int getStatus(int status) {
        if (status == 0) {
            return -1;
        } else {
            return 0;
        }
    }

}
