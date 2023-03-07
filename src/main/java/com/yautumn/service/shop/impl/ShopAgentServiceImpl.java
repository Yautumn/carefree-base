package com.yautumn.service.shop.impl;

import com.yautumn.common.entity.shop.ShopAgentInfo;
import com.yautumn.common.utils.DateUtils;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.dao.shop.ShopAgentInfoMapper;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopAgentParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopAgentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopAgentServiceImpl implements ShopAgentService {

    @Autowired
    private ShopAgentInfoMapper mapper;

    @Override
    public ShopAgentInfo findById(int id) {
        ShopAgentInfo shopAgentInfo = mapper.selectByPrimaryKey(id);
        return shopAgentInfo;
    }

    @Override
    public PageBeanUtil findAll(PageParam param, int shopId) {
        int currentPage = param.getCurrentPage();
        int pageSize = param.getPageSize();
        int totalCount = param.getTotalCount();
        PageBeanUtil pageBeanUtil = new PageBeanUtil(currentPage,pageSize,totalCount);
        List<ShopAgentInfo> shopAgentInfos = mapper.findAll(pageBeanUtil.getStart(),pageBeanUtil.getEnd(),shopId);
        pageBeanUtil.setList(shopAgentInfos);
        return pageBeanUtil;
    }

    @Override
    public String delete(int id) {
        ShopAgentInfo shopAgentInfo = mapper.selectByPrimaryKey(id);
        if (null == shopAgentInfo){
            return ExceptionsEnum.SHOP_AGENT_IS_NOT_EXIST.name;
        }
        shopAgentInfo.setStatus("0");
        shopAgentInfo.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = mapper.updateByPrimaryKey(shopAgentInfo);
        if (i != 1){
            return ExceptionsEnum.SHOP_AGENT_DELETE_ERROR.name;
        }
        return ExceptionsEnum.SUCCESS.name;
    }

    @Override
    public String save(ShopAgentParam param) {
        ShopAgentInfo shopAgentInfo = new ShopAgentInfo();
        BeanUtils.copyProperties(param,shopAgentInfo);
        shopAgentInfo.setStatus("1");
        shopAgentInfo.setCreatetime(DateUtils.dateTimeToString(new Date()));
        int i = mapper.insert(shopAgentInfo);
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }
        return ExceptionsEnum.SHOP_AGENT_INSERT_ERROR.name;
    }

    @Override
    public String update(ShopAgentParam param) {
        ShopAgentInfo shopAgentInfo = mapper.selectByPrimaryKey(param.getId());
        BeanUtils.copyProperties(param,shopAgentInfo);
        shopAgentInfo.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = mapper.updateByPrimaryKey(shopAgentInfo);
        if (i != 1){
            return ExceptionsEnum.SHOP_AGENT_UPDATE_ERROR.name;
        }
        return ExceptionsEnum.SUCCESS.name;
    }

    @Override
    public int count(int shopId) {
        return mapper.countByShopId(shopId);
    }
}
