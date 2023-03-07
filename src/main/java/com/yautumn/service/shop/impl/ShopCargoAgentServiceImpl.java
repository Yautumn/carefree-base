package com.yautumn.service.shop.impl;

import com.yautumn.common.entity.shop.ShopCargoAgentInfo;
import com.yautumn.common.utils.DateUtils;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.dao.shop.ShopCargoAgentInfoMapper;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopCargoAgentParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopCargoAgentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopCargoAgentServiceImpl implements ShopCargoAgentService {

    @Autowired
    private ShopCargoAgentInfoMapper mapper;

    @Override
    public ShopCargoAgentInfo findById(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBeanUtil findAll(PageParam param, int shopId) {
        int currentPage = param.getCurrentPage();
        int pageSize = param.getPageSize();
        int totalCount = param.getTotalCount();
        PageBeanUtil pageBeanUtil = new PageBeanUtil(currentPage,pageSize,totalCount);
        List<ShopCargoAgentInfo> shopCargoAgentInfos = mapper.findAll(pageBeanUtil.getStart(),pageBeanUtil.getEnd(),shopId);
        pageBeanUtil.setList(shopCargoAgentInfos);
        return pageBeanUtil;
    }

    @Override
    public String delete(int id) {
        ShopCargoAgentInfo shopCargoAgentInfo = mapper.selectByPrimaryKey(id);
        if (null == shopCargoAgentInfo){
            return ExceptionsEnum.SHOP_CARGO_IS_NOT_EXIST.name;
        }
        shopCargoAgentInfo.setStatus("0");
        shopCargoAgentInfo.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = mapper.updateByPrimaryKey(shopCargoAgentInfo);
        if (i != 1){
            return ExceptionsEnum.SHOP_CARGO_DELETE_ERROR.name;
        }
        return ExceptionsEnum.SUCCESS.name;
    }

    @Override
    public String save(ShopCargoAgentParam param) {
        ShopCargoAgentInfo shopCargoAgentInfo = new ShopCargoAgentInfo();
        BeanUtils.copyProperties(param,shopCargoAgentInfo);
        shopCargoAgentInfo.setStatus("1");
        shopCargoAgentInfo.setCreatetime(DateUtils.dateTimeToString(new Date()));
        int i = mapper.insert(shopCargoAgentInfo);
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }
        return ExceptionsEnum.SHOP_CARGO_AGENT_INSERT_ERROR.name;
    }

    @Override
    public int count(int shopId) {
        return mapper.countByShopId(shopId);
    }

    @Override
    public String update(ShopCargoAgentParam param) {
        ShopCargoAgentInfo shopCargoAgentInfo = mapper.selectByPrimaryKey(param.getId());
        BeanUtils.copyProperties(param,shopCargoAgentInfo);
        shopCargoAgentInfo.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = mapper.updateByPrimaryKey(shopCargoAgentInfo);
        if (i != 1){
            return ExceptionsEnum.SHOP_CARGO_AGENT_UPDATE_ERROR.name;
        }
        return ExceptionsEnum.SUCCESS.name;
    }
}
