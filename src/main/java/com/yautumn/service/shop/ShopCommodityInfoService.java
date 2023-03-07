package com.yautumn.service.shop;

import com.yautumn.common.entity.shop.ShopCommodityInformation;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopCommodityParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShopCommodityInfoService {

    String insert(ShopCommodityParam shopCommodityParam);

    String delShopCommodityByID(int shopCommodityId);

    String updateCommdityByID(ShopCommodityParam shopCommodityParam);

    PageBeanUtil findCommdityAll(PageParam pageParam);

    ShopCommodityInformation findShopCommodityByID(int shopCommodityId);

    int countCommoditys(int shopId);

    String batchInsert(List<ShopCommodityParam> shopCommodityParams);

    String batchUpdate(List<ShopCommodityParam> shopCommodityParams);
}
