package com.glsx.vasp.admin.modules.goods.entity;

import com.glsx.vasp.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "d_goods_picture")
public class GoodsPicture extends BaseEntity {

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 图片url
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 顺序号
     */
    @Column(name = "pic_seq")
    private Byte picSeq;

    /**
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取图片url
     *
     * @return pic_url - 图片url
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置图片url
     *
     * @param picUrl 图片url
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    /**
     * 获取顺序号
     *
     * @return pic_seq - 顺序号
     */
    public Byte getPicSeq() {
        return picSeq;
    }

    /**
     * 设置顺序号
     *
     * @param picSeq 顺序号
     */
    public void setPicSeq(Byte picSeq) {
        this.picSeq = picSeq;
    }

}