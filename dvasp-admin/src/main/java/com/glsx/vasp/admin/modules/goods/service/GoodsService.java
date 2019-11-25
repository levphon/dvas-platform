package com.glsx.vasp.admin.modules.goods.service;

import com.glsx.vasp.admin.common.utils.ShiroUtils;
import com.glsx.vasp.admin.modules.goods.entity.Goods;
import com.glsx.vasp.admin.modules.goods.entity.GoodsPicture;
import com.glsx.vasp.admin.modules.goods.mapper.GoodsMapper;
import com.glsx.vasp.admin.modules.goods.mapper.GoodsPictureMapper;
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
 * @create 3/29/2019 12:48
 * @since 1.0.0
 */
@Service
public class GoodsService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsPictureMapper goodsPictureMapper;

    public List<Goods> search(Map<String, Object> params) {
        return goodsMapper.search(params);
    }

    public List<Goods> list() {
        return goodsMapper.selectAll();
    }

    public Goods getById(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    public Goods getByName(String name) {
        return goodsMapper.selectByName(name);
    }

    public List<Goods> queryByCategory(String code) {
        return goodsMapper.selectByCategory(code);
    }

    public int save(Goods goods) {
        String[] picUrls = goods.getPicUrl().split(",");
        goods.setPicUrl(picUrls[0]);
        int affectCount = goodsMapper.insert(goods);
        int picCnt = saveGooodsPictures(goods.getId(), picUrls);
        logger.info("保存图片张数：" + picCnt);
        return affectCount;
    }

    public int update(Goods goods) {
        String[] picUrls = goods.getPicUrl().split(",");
        goods.setPicUrl(picUrls[0]);
        int picCnt = saveGooodsPictures(goods.getId(), picUrls);
        logger.info("更新图片张数：" + picCnt);
        goods.setUpdateUser(ShiroUtils.getUserId());
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    private int saveGooodsPictures(Integer goodsId, String[] picUrls) {
        Date now = new Date();
        //删除旧图片
        goodsPictureMapper.logicDeleteByGoodsId(goodsId);
        //插入新图片
        for (int i = 0; i < picUrls.length; i++) {
            GoodsPicture gp = new GoodsPicture();
            gp.setGoodsId(goodsId);
            gp.setPicSeq((byte) i);
            gp.setPicUrl(picUrls[i]);
            gp.setCreateTime(now);
            gp.setCreateUser(ShiroUtils.getUserId());
            goodsPictureMapper.insert(gp);
        }
        return picUrls.length;
    }

    /**
     * 下架
     *
     * @param goods
     * @return
     */
    public int pullOffShelves(Goods goods) {
        goods.setUpdateUser(ShiroUtils.getUserId());
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    public List<GoodsPicture> listGoodsPicturesByGoodsId(Integer goodsId) {
        return goodsPictureMapper.selectGoodsPicturesByGoodsId(goodsId);
    }

}
