package com.yautumn.service.shop.impl;

import com.yautumn.common.entity.shop.ShopCargoProviderInfo;
import com.yautumn.common.utils.DateUtils;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.dao.shop.ShopCargoProviderInfoMapper;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopCargoProviderParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopCargoProviderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopCargoProviderServiceImpl implements ShopCargoProviderService {

    @Autowired
    private ShopCargoProviderInfoMapper mapper;

    @Override
    public ShopCargoProviderInfo findById(int id) {
        ShopCargoProviderInfo shopCargoProviderInfo = mapper.selectByPrimaryKey(id);
        return shopCargoProviderInfo;
    }

    @Override
    public String save(ShopCargoProviderParam param) {
        ShopCargoProviderInfo shopCargoProviderInfo = new ShopCargoProviderInfo();
        BeanUtils.copyProperties(param,shopCargoProviderInfo);
        shopCargoProviderInfo.setCreatetime(DateUtils.dateTimeToString(new Date()));
        shopCargoProviderInfo.setStatus("1");
        int i = mapper.insert(shopCargoProviderInfo);
        if (i != 1){
            return ExceptionsEnum.SHOP_CARGO_INSERT_ERROR.name;
        }
        return ExceptionsEnum.SUCCESS.name;
    }

    @Override
    public String update(ShopCargoProviderParam param) {
        ShopCargoProviderInfo shopCargoProviderInfo = new ShopCargoProviderInfo();
        BeanUtils.copyProperties(param,shopCargoProviderInfo);
        shopCargoProviderInfo.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = mapper.updateByPrimaryKey(shopCargoProviderInfo);
        if (i != 1){
            return ExceptionsEnum.SHOP_CARGO_UPDATE_ERROR.name;
        }
        return ExceptionsEnum.SUCCESS.name;
    }

    @Override
    public String delete(int id) {
        ShopCargoProviderInfo shopCargoProviderInfo = this.findById(id);
        if (null == shopCargoProviderInfo){
            return ExceptionsEnum.SHOP_CARGO_IS_NOT_EXIST.name;
        }
        shopCargoProviderInfo.setStatus("0");
        int i = mapper.updateByPrimaryKey(shopCargoProviderInfo);
        if (i != 1){
            return ExceptionsEnum.SHOP_CARGO_DELETE_ERROR.name;
        }
        return ExceptionsEnum.SUCCESS.name;
    }

    @Override
    public int count(int shopId) {
        return mapper.selectCount(shopId);
    }

    @Override
    public PageBeanUtil findAll(PageParam param,int shopId) {
        int currentPage = param.getCurrentPage();
        int pageSize = param.getPageSize();
        int totalCount = param.getTotalCount();
        PageBeanUtil pageBeanUtil = new PageBeanUtil(currentPage,pageSize,totalCount);
        List<ShopCargoProviderInfo> shopCargoProviderInfos = mapper.findByShopId(shopId);
        pageBeanUtil.setList(shopCargoProviderInfos);
        return pageBeanUtil;
    }
}
