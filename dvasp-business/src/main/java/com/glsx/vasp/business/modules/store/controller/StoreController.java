package com.glsx.vasp.business.modules.store.controller;

import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.common.exception.ResultCodeEnum;
import com.glsx.vasp.business.modules.AbstractController;
import com.glsx.vasp.business.modules.store.entity.Store;
import com.glsx.vasp.business.modules.store.model.StoreModel;
import com.glsx.vasp.business.modules.store.service.StoreService;
import com.glsx.vasp.business.modules.user.entity.StoreUser;
import com.glsx.vasp.business.modules.user.service.StoreUserService;
import com.glsx.vasp.validator.ValidatorUtils;
import com.glsx.vasp.web.R;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author liumh
 * @create 3/26/2019 15:10
 * @since 1.0.0
 */
@RestController
@RequestMapping("/store")
public class StoreController extends AbstractController {

    @Autowired
    private StoreService storeService;
    @Autowired
    StoreUserService storeUserService;

    /**
     * 新增门店
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(StoreModel model) {
        logger.info(model.toString());
        ValidatorUtils.validateEntity(model);
        StoreUser user = getSessionUser();
        logger.info(user.toString());
        //相同sp不能存在相同门店名称的门店
        Map<String, Object> params = new HashMap<>();
        params.put("orgId",model.getOrgId());
        List<Store> list = storeService.search(params);
        if (CollectionUtils.isNotEmpty(list)){
            for (Store store:list) {
                if (store.getPhone().equals(model.getPhone()))
                    return R.error(ResultCodeEnum.STORE_IS_EXIST_PHONE.getCode(), ResultCodeEnum.STORE_IS_EXIST_PHONE.getMsg());
                if (store.getName().equals(model.getName()))
                    return R.error(ResultCodeEnum.STORE_IS_STOP_NAME.getCode(), ResultCodeEnum.STORE_IS_STOP_NAME.getMsg());
            }
        }
        model.setPhone(user.getPhone());
        logger.info(model.toString());
        int id = storeService.save(model);
        user.setStoreId(id);
        user.setEnableStatus(Constants.ENABLE_STATUS_CHECK);
        storeUserService.saveOrUpdate(user);
        return R.ok();
    }


}
