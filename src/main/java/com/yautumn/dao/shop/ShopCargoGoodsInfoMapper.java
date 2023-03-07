package com.yautumn.dao.shop;

import com.yautumn.common.entity.shop.ShopCargoGoodsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopCargoGoodsInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShopCargoGoodsInfo record);

    int insertSelective(ShopCargoGoodsInfo record);

    ShopCargoGoodsInfo selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(ShopCargoGoodsInfo record);

    int updateByPrimaryKey(ShopCargoGoodsInfo record);

    List<ShopCargoGoodsInfo> findAll(int start, int end, int shopId);

    int batchInsert(List<ShopCargoGoodsInfo> shopCargoGoodsInfos);

    int countByShopId(int shopId);
}