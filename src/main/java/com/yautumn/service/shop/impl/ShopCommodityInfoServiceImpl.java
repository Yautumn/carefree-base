package com.yautumn.service.shop.impl;

import com.yautumn.common.entity.shop.ShopCommodityInformation;
import com.yautumn.common.utils.BatchUtils;
import com.yautumn.common.utils.DateUtils;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.dao.shop.ShopCommodityInformationMapper;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopCommodityParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopCommodityInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShopCommodityInfoServiceImpl implements ShopCommodityInfoService {

    @Autowired
    private ShopCommodityInformationMapper shopCommodityInformationMapper;

    @Autowired
    private BatchUtils batchUtils;

    /**
     * 插入商品信息
     * @param shopCommodityParam
     * @return
     */
    @Override
    public String insert(ShopCommodityParam shopCommodityParam) {
        ShopCommodityInformation shopCommodityInformation = new ShopCommodityInformation();
        BeanUtils.copyProperties(shopCommodityParam,shopCommodityInformation);
        shopCommodityInformation.setCreatetime(DateUtils.dateTimeToString(new Date()));
        int i = shopCommodityInformationMapper.insert(shopCommodityInformation);
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_INSERT_ERROR.name;
        }
    }

    /**
     * 根据id删除商品信息
     * @param shopCommodityId
     * @return
     */
    @Override
    public String delShopCommodityByID(int shopCommodityId) {
        if (isNull(shopCommodityId)) {
            return "商品信息不存在";
        }
        int i = shopCommodityInformationMapper.deleteByPrimaryKey(shopCommodityId);
        if (i == 1) {
            return ExceptionsEnum.SUCCESS.name;
        } else {
            return ExceptionsEnum.SHOP_DELETE_ERROR.name;
        }
    }

    /**
     * 更新商品信息
     * @param shopCommodityParam
     * @return
     */
    @Override
    public String updateCommdityByID(ShopCommodityParam shopCommodityParam) {
        if(this.isNull(shopCommodityParam.getId())){
            return ExceptionsEnum.SHOP_IS_NOT_EXIST.name;
        }
        ShopCommodityInformation shopCommodityInformation = this.findShopCommodityByID(shopCommodityParam.getId());
        BeanUtils.copyProperties(shopCommodityParam,shopCommodityInformation);
        shopCommodityInformation.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = shopCommodityInformationMapper.updateByPrimaryKey(shopCommodityInformation);
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_UPDATE_ERROR.name;
        }
    }

    /**
     * 分页查询商品信息
     * @param pageParam
     * @return
     */
    @Override
    public PageBeanUtil findCommdityAll(PageParam pageParam) {
        int currentPage = pageParam.getCurrentPage();
        int pageSize = pageParam.getPageSize();
        int totalCount = pageParam.getTotalCount();
        PageBeanUtil pageBeanUtil = new PageBeanUtil(currentPage,pageSize,totalCount);
        List<ShopCommodityInformation> shopCommodityInformations = shopCommodityInformationMapper.findCommodityAll(pageBeanUtil.getStart(),pageBeanUtil.getEnd());
        pageBeanUtil.setList(shopCommodityInformations);
        return pageBeanUtil;
    }


    /**
     * 根据id查询商品信息
     * @param shopCommodityId
     * @return
     */
    @Override
    public ShopCommodityInformation findShopCommodityByID(int shopCommodityId) {
        ShopCommodityInformation shopCommodityInformation = shopCommodityInformationMapper.selectByPrimaryKey(shopCommodityId);
        return shopCommodityInformation;
    }

    @Override
    public int countCommoditys(int shopId) {
        int num = shopCommodityInformationMapper.selectCount(shopId);
        return num;
    }

    @Override
    @Transactional
    public String batchInsert(List<ShopCommodityParam> shopCommodityParams) {
        List<ShopCommodityInformation> shopCommodityInformations = new ArrayList<ShopCommodityInformation>();
        shopCommodityParams.forEach(shopCommodityParam -> {
            ShopCommodityInformation shopCommodityInformation = new ShopCommodityInformation();
            BeanUtils.copyProperties(shopCommodityParam,shopCommodityInformation);
            shopCommodityInformation.setCreatetime(DateUtils.dateTimeToString(new Date()));
            shopCommodityInformations.add(shopCommodityInformation);
        });
        int i = batchUtils.batchUpdateOrInsert(shopCommodityInformations,ShopCommodityInformationMapper.class,(item,hopCommodityInformationMapper)->shopCommodityInformationMapper.insert(item));
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_BATCH_INSERT_ERROR.name;
        }
    }

    @Override
    @Transactional
    public String batchUpdate(List<ShopCommodityParam> shopCommodityParams) {
        List<ShopCommodityInformation> shopCommodityInformations = new ArrayList<ShopCommodityInformation>();
        shopCommodityParams.forEach(shopCommodityParam -> {
            ShopCommodityInformation shopCommodityInformation = new ShopCommodityInformation();
            BeanUtils.copyProperties(shopCommodityParam,shopCommodityInformation);
            shopCommodityInformation.setCreatetime(DateUtils.dateTimeToString(new Date()));
            shopCommodityInformations.add(shopCommodityInformation);
        });
        int i = batchUtils.batchUpdateOrInsert(shopCommodityInformations,ShopCommodityInformationMapper.class,(item,hopCommodityInformationMapper)->shopCommodityInformationMapper.updateByPrimaryKey(item));
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_BATCH_UPDATE_ERROR.name;
        }
    }


    private boolean isNull(int shopCommodityId){
        boolean flag = false;
        ShopCommodityInformation shopCommodityInformation = this.findShopCommodityByID(shopCommodityId);
        if (null == shopCommodityInformation){
            flag = true;
        }
        return flag;
    }

}
