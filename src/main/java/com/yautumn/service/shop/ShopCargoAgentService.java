package com.yautumn.service.shop;

import com.yautumn.common.entity.shop.ShopCargoAgentInfo;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopCargoAgentParam;

public interface ShopCargoAgentService {
    ShopCargoAgentInfo findById(int id);

    PageBeanUtil findAll(PageParam param, int shopId);

    String delete(int id);

    String save(ShopCargoAgentParam param);

    int count(int shopId);

    String update(ShopCargoAgentParam param);
}
