package com.yautumn.dao.market;

import com.yautumn.common.entity.market.MarketInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MarketInfo record);

    int insertSelective(MarketInfo record);

    MarketInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MarketInfo record);

    int updateByPrimaryKey(MarketInfo record);

    List<MarketInfo> findAll(int start, int end);

    int selectCount();
}