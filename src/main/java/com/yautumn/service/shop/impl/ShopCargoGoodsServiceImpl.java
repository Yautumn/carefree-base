package com.yautumn.service.shop.impl;

import com.yautumn.common.entity.shop.ShopCargoGoodsInfo;
import com.yautumn.common.utils.BatchUtils;
import com.yautumn.common.utils.DateUtils;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.dao.shop.ShopCargoGoodsInfoMapper;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopCargoGoodsParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopCargoGoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShopCargoGoodsServiceImpl implements ShopCargoGoodsService {

    @Autowired
    private ShopCargoGoodsInfoMapper mapper;

    @Autowired
    private BatchUtils batchUtils;

    @Override
    public ShopCargoGoodsInfo findById(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBeanUtil findAll(PageParam param, int shopId) {
        int currentPage = param.getCurrentPage();
        int pageSize = param.getPageSize();
        int totalCount = param.getTotalCount();
        PageBeanUtil pageBeanUtil = new PageBeanUtil(currentPage,pageSize,totalCount);
        List<ShopCargoGoodsInfo> shopCargoGoodsInfos = mapper.findAll(pageBeanUtil.getStart(),pageBeanUtil.getEnd(),shopId);
        pageBeanUtil.setList(shopCargoGoodsInfos);
        return pageBeanUtil;
    }

    @Override
    public String delete(int id) {
        ShopCargoGoodsInfo shopCargoGoodsInfo = mapper.selectByPrimaryKey(id);
        if (null == shopCargoGoodsInfo){
            return ExceptionsEnum.SHOP_CARGO_GOODS_IS_NOT_EXIST.name;
        }
        shopCargoGoodsInfo.setStatus("0");
        shopCargoGoodsInfo.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = mapper.updateByPrimaryKey(shopCargoGoodsInfo);
        if (i != 1){
            return ExceptionsEnum.SHOP_CARGO_GOODS_DELETE_ERROR.name;
        }
        return ExceptionsEnum.SUCCESS.name;
    }

    @Override
    public String save(ShopCargoGoodsParam param) {
        ShopCargoGoodsInfo shopCargoGoodsInfo = new ShopCargoGoodsInfo();
        BeanUtils.copyProperties(param,shopCargoGoodsInfo);
        shopCargoGoodsInfo.setStatus("1");
        shopCargoGoodsInfo.setCreatetime(DateUtils.dateTimeToString(new Date()));
        int i = mapper.insert(shopCargoGoodsInfo);
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }
        return ExceptionsEnum.SHOP_CARGO_GOODS_INSERT_ERROR.name;
    }

    @Override
    public int count(int shopId) {
        return mapper.countByShopId(shopId);
    }

    @Override
    @Transactional
    public String batchInsert(List<ShopCargoGoodsParam> params) {
        List<ShopCargoGoodsInfo> shopCargoGoodsInfos = new ArrayList<ShopCargoGoodsInfo>();
        params.forEach(shopCommodityParam -> {
            ShopCargoGoodsInfo shopCargoGoodsInfo = new ShopCargoGoodsInfo();
            BeanUtils.copyProperties(shopCommodityParam,shopCargoGoodsInfo);
            shopCargoGoodsInfo.setCreatetime(DateUtils.dateTimeToString(new Date()));
            shopCargoGoodsInfos.add(shopCargoGoodsInfo);
        });
        int i = batchUtils.batchUpdateOrInsert(shopCargoGoodsInfos, ShopCargoGoodsInfoMapper.class,(item, shopCargoGoodsInfoMapper)->mapper.insert(item));
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_CARGO_GOODS_BATCH_INSERT_ERROR.name;
        }
    }

    @Override
    @Transactional
    public String batchUpdate(List<ShopCargoGoodsParam> params) {
        List<ShopCargoGoodsInfo> shopCargoGoodsInfos = new ArrayList<ShopCargoGoodsInfo>();
        params.forEach(shopCommodityParam -> {
            ShopCargoGoodsInfo shopCargoGoodsInfo = mapper.selectByPrimaryKey(shopCommodityParam.getId());
            BeanUtils.copyProperties(shopCommodityParam,shopCargoGoodsInfo);
            shopCargoGoodsInfo.setUpdatetime(DateUtils.dateTimeToString(new Date()));
            shopCargoGoodsInfos.add(shopCargoGoodsInfo);
        });
        int i = batchUtils.batchUpdateOrInsert(shopCargoGoodsInfos,ShopCargoGoodsInfoMapper.class,(item,shopCargoGoodsInfoMapper)->shopCargoGoodsInfoMapper.updateByPrimaryKey(item));
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_CARGO_GOODS_BATCH_UPDATE_ERROR.name;
        }
    }

    @Override
    public String update(ShopCargoGoodsParam param) {
        ShopCargoGoodsInfo shopCargoGoodsInfo = mapper.selectByPrimaryKey(param.getId());
        BeanUtils.copyProperties(param,shopCargoGoodsInfo);
        shopCargoGoodsInfo.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = mapper.updateByPrimaryKey(shopCargoGoodsInfo);
        if (i != 1){
            return ExceptionsEnum.SHOP_CARGO_GOODS_UPDATE_ERROR.name;
        }
        return ExceptionsEnum.SUCCESS.name;
    }
}
