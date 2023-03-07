package com.yautumn.service.shop.impl;

import com.yautumn.common.entity.shop.ShopMemberInfo;
import com.yautumn.common.utils.DateUtils;
import com.yautumn.dao.shop.ShopMemberInfoMapper;
import com.yautumn.param.request.shop.ShopMemberParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopMemberServiceImpl implements ShopMemberService {

    @Value("${shopMemberMax}")
    private int shopMemberMax;

    @Autowired
    private ShopMemberInfoMapper shopMemberInfoMapper;
    @Override
    public String save(ShopMemberParam shopMemberParam) {
        int count = this.countMember(shopMemberParam.getShopId());
        if (count >= shopMemberMax){
            return ExceptionsEnum.MEMBER_IS_MAX.name;
        }
        List<ShopMemberInfo> members = this.findMemberByShopId(shopMemberParam.getShopId());
        ShopMemberInfo shopMemberInfo = new ShopMemberInfo();
        BeanUtils.copyProperties(shopMemberParam,shopMemberInfo);
        if (members.isEmpty()){
            shopMemberInfo.setAssistantType("01");
        }else if("01".equals(shopMemberParam.getAssistantType())){
            return ExceptionsEnum.MEMBER_ADMIN_IS_ONLY_ONE.name;
        }
        shopMemberInfo.setStatus("1");
        shopMemberInfo.setCreatetime(DateUtils.dateTimeToString(new Date()));
        int i = shopMemberInfoMapper.insert(shopMemberInfo);
        if (i == 1){
            return ExceptionsEnum.SUCCESS.name;
        }else {
            return ExceptionsEnum.MEMBER_INSERT_ERROR.name;
        }
    }


    public List<ShopMemberInfo> findMemberByShopId(int shopId){
        return shopMemberInfoMapper.findByShopId(shopId);
    }

    @Override
    public ShopMemberInfo findMemberById(int id) {
        return shopMemberInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public String deleteMemberById(int id) {
        ShopMemberInfo shopMemberInfo = this.findMemberById(id);
        if (null == shopMemberInfo){
            return ExceptionsEnum.MEMBER_IS_NOT_EXIST.name;
        }
        shopMemberInfo.setStatus("0");
        int i = shopMemberInfoMapper.updateByPrimaryKey(shopMemberInfo);
        if (i == 1) {
            return ExceptionsEnum.SUCCESS.name;
        } else {
            return ExceptionsEnum.MEMBER_DELETE_ERROR.name;
        }
    }

    public int countMember(int shopId){
        return shopMemberInfoMapper.selectCount(shopId);
    }
}
