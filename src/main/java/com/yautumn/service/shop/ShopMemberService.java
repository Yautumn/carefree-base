package com.yautumn.service.shop;

import com.yautumn.common.entity.shop.ShopMemberInfo;
import com.yautumn.param.request.shop.ShopMemberParam;

import java.util.List;

public interface ShopMemberService {
    public String save(ShopMemberParam shopMemberParam);

    public List<ShopMemberInfo> findMemberByShopId(int shopId);

    public ShopMemberInfo findMemberById(int id);

    String deleteMemberById(int id);
}
