package com.yautumn.service.shop;

import com.yautumn.common.entity.shop.ShopCargoGoodsInfo;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopCargoGoodsParam;

import java.util.List;

public interface ShopCargoGoodsService {
    ShopCargoGoodsInfo findById(int id);

    PageBeanUtil findAll(PageParam param, int shopId);

    String delete(int id);

    String save(ShopCargoGoodsParam param);

    String update(ShopCargoGoodsParam param);

    int count(int shopId);

    String batchUpdate(List<ShopCargoGoodsParam> params);

    String batchInsert(List<ShopCargoGoodsParam> params);
}
