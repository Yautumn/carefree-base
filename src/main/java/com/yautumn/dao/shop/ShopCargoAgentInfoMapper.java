package com.yautumn.dao.shop;

import com.yautumn.common.entity.shop.ShopCargoAgentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopCargoAgentInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShopCargoAgentInfo record);

    int insertSelective(ShopCargoAgentInfo record);

    ShopCargoAgentInfo selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(ShopCargoAgentInfo record);

    int updateByPrimaryKey(ShopCargoAgentInfo record);

    List<ShopCargoAgentInfo> findAll(int start, int end, int shopId);

    int countByShopId(int shopId);
}