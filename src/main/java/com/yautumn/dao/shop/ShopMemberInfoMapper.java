package com.yautumn.dao.shop;

import com.yautumn.common.entity.shop.ShopMemberInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMemberInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShopMemberInfo record);

    int insertSelective(ShopMemberInfo record);

    ShopMemberInfo selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(ShopMemberInfo record);

    int updateByPrimaryKey(ShopMemberInfo record);

    int selectCount(int shopId);

    List<ShopMemberInfo> findByShopId(int shopId);
}