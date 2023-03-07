package com.yautumn.service.shop.impl;

import com.yautumn.common.entity.shop.ShopInfo;
import com.yautumn.common.utils.DateUtils;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.dao.shop.ShopInfoMapper;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopInfoServiceImpl implements ShopInfoService {

    private final Logger logger = LoggerFactory.getLogger(ShopInfoServiceImpl.class);

    @Autowired
    private ShopInfoMapper shopInfoMapper;

    /**
     * 新增商户信息
     * @param shopParam
     * @return
     */
    @Override
    public String insert(ShopParam shopParam) {
        ShopInfo shopInfo = new ShopInfo();
        BeanUtils.copyProperties(shopParam,shopInfo);
        shopInfo.setCreatetime(DateUtils.dateTimeToString(new Date()));
        int i = shopInfoMapper.insert(shopInfo);
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_INSERT_ERROR.name;
        }
    }

    /**
     * 根据id删除商户信息
     * @param shopId
     */
    @Override
    public String delShopByID(int shopId) {
        if (isNull(shopId)) {
            return ExceptionsEnum.SHOP_IS_NOT_EXIST.name;
        }
        ShopInfo shopInfo = this.findShopById(shopId);
        shopInfo.setStatus("0");
        shopInfo.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = shopInfoMapper.updateByPrimaryKey(shopInfo);
        if (i == 1) {
            return ExceptionsEnum.SUCCESS.name;
        } else {
            return ExceptionsEnum.SHOP_DELETE_ERROR.name;
        }
    }

    /**
     * 更新商户信息
     * @param shopParam
     * @return
     */
    @Override
    public String updateShop(ShopParam shopParam) {
        if (this.isNull(shopParam.getId())){
            return ExceptionsEnum.SHOP_IS_NOT_EXIST.name;
        }
        ShopInfo shopInfoNew = this.findShopById(shopParam.getId());
        BeanUtils.copyProperties(shopParam,shopInfoNew);
        shopInfoNew.setUpdatetime(DateUtils.dateTimeToString(new Date()));
        int i = shopInfoMapper.updateByPrimaryKey(shopInfoNew);
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.SHOP_UPDATE_ERROR.name;
        }
    }

    /**
     * 根据id查询商户信息
     * @param shopId
     * @return
     */
    @Override
    public ShopInfo findShopById(int shopId) {
        return shopInfoMapper.selectByPrimaryKey(shopId);
    }

    /**
     * 分页查询数据
     * @param pageParam
     * @return
     */
    @Override
    public PageBeanUtil findShopAll(PageParam pageParam) {
        int currentPage = pageParam.getCurrentPage();
        int pageSize = pageParam.getPageSize();
        int totalCount = pageParam.getTotalCount();
        PageBeanUtil pageBeanUtil = new PageBeanUtil(currentPage,pageSize,totalCount);
        List<ShopInfo> shopInfos = shopInfoMapper.findShopAll(pageBeanUtil.getStart(),pageBeanUtil.getEnd());
        pageBeanUtil.setList(shopInfos);
        return pageBeanUtil;
    }


    /**
     * 查询商户总数
     * @return
     */
    public int countShop(){
        int num = shopInfoMapper.selectCount();
        return num;
    }

    private boolean isNull(int shopId){
        boolean flag = false;
        ShopInfo shopInfo = this.findShopById(shopId);
        if (null == shopInfo){
            flag = true;
        }
        return flag;
    }

}
