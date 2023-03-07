package com.yautumn.service.shop;

import com.yautumn.common.entity.shop.ShopFactoryInfo;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopFactoryInfoParam;

public interface ShopFactoryInfoService {

    String save(ShopFactoryInfoParam param);

    String del(int id);

    ShopFactoryInfo findById(int id);

    String update(ShopFactoryInfoParam param);

    PageBeanUtil findAllByPage(PageParam param);

    int count(int shopId);

}
