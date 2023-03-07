package com.yautumn.service.shop;

import com.yautumn.common.entity.shop.ShopCargoProviderInfo;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopCargoProviderParam;
import org.springframework.stereotype.Service;

@Service
public interface ShopCargoProviderService {

    ShopCargoProviderInfo findById(int id);

    String save(ShopCargoProviderParam param);

    String update(ShopCargoProviderParam param);

    String delete(int id);

    int count(int shopId);

    PageBeanUtil findAll(PageParam param,int shopId);
}
