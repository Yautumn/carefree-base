package com.yautumn.dao.shop;

import com.yautumn.common.entity.shop.ShopAgentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopAgentInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShopAgentInfo record);

    int insertSelective(ShopAgentInfo record);

    ShopAgentInfo selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(ShopAgentInfo record);

    int updateByPrimaryKey(ShopAgentInfo record);

    List<ShopAgentInfo> findAll(Integer start, int end,int shopId);

    int countByShopId(int shopId);
}