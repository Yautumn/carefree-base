package com.yautumn.dao.shop;

import com.yautumn.common.entity.shop.ShopCargoProviderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopCargoProviderInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShopCargoProviderInfo record);

    int insertSelective(ShopCargoProviderInfo record);

    ShopCargoProviderInfo selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(ShopCargoProviderInfo record);

    int updateByPrimaryKey(ShopCargoProviderInfo record);

    int selectCount(int shopId);

    List<ShopCargoProviderInfo> findByShopId(int shopId);
}