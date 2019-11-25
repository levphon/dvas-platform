package com.glsx.vasp.business.modules.store.service;

import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.modules.store.entity.Store;
import com.glsx.vasp.business.modules.store.mapper.StoreMapper;
import com.glsx.vasp.business.modules.store.model.StoreModel;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private StoreMapper storeMapper;

    public List<Store> search(Map<String, Object> params) {

        return storeMapper.search(params);
    }

    public List<Store> list() {
        return null;
    }

    public int save(StoreModel storeModel) {
        Store store = new Store();
        BeanUtils.copyProperties(storeModel,store);
        System.out.println(store.toString());
        store.setCreateTime(new Date());
        store.setEnableStatus(Constants.ENABLE_STATUS_CHECK);
        //一个手机号只能有一个门店
        storeMapper.insertUseGeneratedKeys(store);
        return store.getId();
    }

    public  Store selectByPrimaryKey(Integer id){

      return  storeMapper.selectByPrimaryKey(id);
    }
}
