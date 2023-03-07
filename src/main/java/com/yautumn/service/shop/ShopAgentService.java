package com.yautumn.service.shop;

import com.yautumn.common.entity.shop.ShopAgentInfo;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopAgentParam;

public interface ShopAgentService {
    ShopAgentInfo findById(int id);

    PageBeanUtil findAll(PageParam param, int shopId);

    String delete(int id);

    String save(ShopAgentParam param);

    String update(ShopAgentParam param);

    int count(int shopId);
}
