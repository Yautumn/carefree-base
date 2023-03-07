package com.yautumn.service.shop.impl;

import com.yautumn.common.entity.shop.ShopFactoryInfo;
import com.yautumn.common.utils.DateUtils;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.dao.shop.ShopFactoryInfoMapper;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopFactoryInfoParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopFactoryInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopFactoryInfoServiceImpl implements ShopFactoryInfoService {

    @Autowired
    private ShopFactoryInfoMapper mapper;
    @Override
    public String save(ShopFactoryInfoParam param) {
        ShopFactoryInfo shopFactoryInfo = new ShopFactoryInfo();
        BeanUtils.copyProperties(param,shopFactoryInfo);
        shopFactoryInfo.setStatus("1");
        shopFactoryInfo.setCreatetime(DateUtils.dateTimeToString(new Date()));
        int i = mapper.insert(shopFactoryInfo);
        if (i != 1){
            return ExceptionsEnum.SHOP_FACTORY_INSERT_ERROR.name;
        }
        return ExceptionsEnum.SUCCESS.name;
    }

    @Override
    public String del(int id) {
        ShopFactoryInfo shopFactoryInfo = mapper.selectByPrimaryKey(id);
        if (null == shopFactoryInfo){
            return ExceptionsEnum.SHOP_FACTORY_IS_NOT_EXIST.name;
        }
        shopFactoryInfo.setStatus("0");
        int i = mapper.updateByPrimaryKey(shopFactoryInfo);
        if (i != 1){
            return ExceptionsEnum.SHOP_FACTORY_DELETE_ERROR.name;
        }
        return ExceptionsEnum.SUCCESS.name;
    }

    @Override
    public ShopFactoryInfo findById(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public String update(ShopFactoryInfoParam param) {
        ShopFactoryInfo shopFactoryInfo = new ShopFactoryInfo();
        BeanUtils.copyProperties(param,shopFactoryInfo);
        shopFactoryInfo.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = mapper.updateByPrimaryKey(shopFactoryInfo);
        if (i != 1){
            return ExceptionsEnum.SHOP_FACTORY_UPDATE_ERROR.name;
        }
        return ExceptionsEnum.SUCCESS.name;
    }

    @Override
    public PageBeanUtil findAllByPage(PageParam param) {
        int currentPage = param.getCurrentPage();
        int pageSize = param.getPageSize();
        int totalCount = param.getTotalCount();
        PageBeanUtil pageBeanUtil = new PageBeanUtil(currentPage,pageSize,totalCount);
        List<ShopFactoryInfo> shopFactoryInfos = mapper.selectByPage(pageBeanUtil.getStart(),pageBeanUtil.getEnd());
        pageBeanUtil.setList(shopFactoryInfos);
        return pageBeanUtil;
    }

    @Override
    public int count(int shopId) {
        int count = mapper.selectCount(shopId);
        return count;
    }
}
