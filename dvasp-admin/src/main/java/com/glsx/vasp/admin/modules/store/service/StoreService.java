package com.glsx.vasp.admin.modules.store.service;

import com.glsx.vasp.admin.common.exception.AdminException;
import com.glsx.vasp.admin.common.utils.ShiroUtils;
import com.glsx.vasp.admin.modules.store.entity.Store;
import com.glsx.vasp.admin.modules.store.entity.StoreUser;
import com.glsx.vasp.admin.modules.store.mapper.StoreMapper;
import com.glsx.vasp.admin.modules.store.mapper.StoreUserMapper;
import com.glsx.vasp.admin.modules.store.model.StoreExportModel;
import com.glsx.vasp.admin.modules.store.model.StoreRegionVO;
import com.glsx.vasp.admin.modules.sys.service.AdministrativeDivisionService;
import com.glsx.vasp.enums.SysContants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 3/26/2019 15:09
 * @since 1.0.0
 */
@Service
public class StoreService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private StoreUserMapper storeUserMapper;

    @Autowired
    private AdministrativeDivisionService divisionService;

    public List<Store> search(Map<String, Object> params) {
        return storeMapper.search(params);
    }

    public List<StoreExportModel> export(Map<String, Object> params) {
        return storeMapper.export(params);
    }

    public List<Store> list(Map<String, Object> params) {
        return storeMapper.selectAll();
    }

    public List<StoreRegionVO> listByName(Map<String, Object> params) {
        return storeMapper.selectListByName(params);
    }

    public Store getById(Integer storeId) {
        Store store = storeMapper.selectByPrimaryKey(storeId);
        //StringUtils.covertNullToEmptyStr(store.getAddress()
        store.setFullAddress(divisionService.getAddressByArea(store.getDistrict()));//显示 省份地市区域   详细地址：address
        List<StoreUser> storeUserList = storeUserMapper.selectByStoreId(store.getId());
        StringBuilder phones = new StringBuilder(store.getPhone());
        for (StoreUser su : storeUserList) {
            if (!store.getPhone().equals(su.getPhone())) phones.append(",").append(su.getPhone());
        }
        store.setPhone(phones.toString());
        return store;
    }

    public void save(Store store) {
        String[] phones = store.getPhone().split(",");
        //校验手机号是否已经绑定其他门店
        for (String phone : phones) {
            StoreUser storeUser = storeUserMapper.selectByPhone(phone);
            if (storeUser != null) throw AdminException.create(String.format("手机号%s已绑定其他门店！", phone));
        }

        //新增门店用户
        store.setPhone(phones[0]);
        storeMapper.insert(store);
        for (String phone : phones) {
            StoreUser storeUser = new StoreUser();
            storeUser.setPhone(phone);
            storeUser.setStoreId(store.getId());
            storeUser.setEnableStatus(SysContants.EnableStatus.enable.getCode());
            storeUser.setCreateUser(ShiroUtils.getUserId());
            storeUser.setCreateTime(new Date());
            storeUserMapper.insert(storeUser);
        }
    }

    public void update(Store store) {
        List<StoreUser> storeUserList = storeUserMapper.selectByStoreId(store.getId());
        boolean existFlag = false;
        StoreUser storeUser = null;
        //判断数据库的手机账号是否在这次修改的号码中
        for (StoreUser su : storeUserList) {
            storeUser = su;
            existFlag = store.getPhone().contains(su.getPhone());
        }
        //不在则删除
        if (!existFlag && storeUser != null) {
            storeUserMapper.logicDelete(storeUser.getId());
        }
        //新增新号码
        String[] phones = store.getPhone().split(",");
        store.setPhone(phones[0]);
        for (String phone : phones) {
            StoreUser su = storeUserMapper.selectByPhone(phone);
            if (su != null) {
                if (!su.getStoreId().equals(store.getId()))//不是同一个门店
                    throw AdminException.create(String.format("%s已经绑定其他门店！", phone));
            } else {
                //如果号码账号不存在，新增
                su = new StoreUser();
                su.setPhone(phone);
                su.setStoreId(store.getId());
                su.setEnableStatus(SysContants.EnableStatus.enable.getCode());
                su.setCreateUser(ShiroUtils.getUserId());
                su.setCreateTime(new Date());
                storeUserMapper.insert(su);
            }
        }
        storeMapper.updateByPrimaryKeySelective(store);
    }

    public Store getByName(String name) {
        return storeMapper.selectByName(name);
    }

    public int changeStatusById(Integer storeId, Integer status) {
        int chgCnt = storeUserMapper.changeStatusByStoreId(storeId, status);
        logger.info(String.format("操作用户ID为%d，门店ID为%d，同步改变门店用户状态个数%d。", ShiroUtils.getUserId(), storeId, chgCnt));
        return storeMapper.changeStatusById(storeId, status);
    }

    public int logicDeleteBatch(Integer[] storeIds) {
        for (Integer storeId : storeIds) {
            int delCnt = storeUserMapper.logicDeleteByStoreId(storeId);
            logger.info(String.format("操作用户ID为%d，门店ID为%d，同步删除门店用户个数%d。", ShiroUtils.getUserId(), storeId, delCnt));
        }
        return storeMapper.logicDeleteBatch(storeIds);
    }

}
