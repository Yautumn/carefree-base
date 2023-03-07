package com.yautumn.dao.shop;

import com.yautumn.common.entity.shop.ShopFactoryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopFactoryInfoMapper {
    int deleteByPrimaryKey(int id);

    int insert(ShopFactoryInfo record);

    int insertSelective(ShopFactoryInfo record);

    ShopFactoryInfo selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(ShopFactoryInfo record);

    int updateByPrimaryKey(ShopFactoryInfo record);

    List<ShopFactoryInfo> selectByPage(int start,int end);

    int selectCount(int shopId);
}