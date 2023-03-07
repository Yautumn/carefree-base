package com.yautumn.controller;

import com.yautumn.common.entity.shop.ShopMemberInfo;
import com.yautumn.common.utils.ResultUtil;
import com.yautumn.param.request.shop.ShopMemberParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "商户成员信息接口")
@RestController
@RequestMapping("shop/member")
public class ShopMemberController {

    @Autowired
    private ShopMemberService shopMemberService;

    @ApiOperation(value = "添加商户成员信息方法")
    @PostMapping("/save")
    public ResultUtil save(@RequestBody ShopMemberParam shopMemberParam){
        String msg = shopMemberService.save(shopMemberParam);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "根据id商户成员信息方法")
    @PostMapping("/find/id")
    public ResultUtil findById(@RequestBody int id){
        ShopMemberInfo shopMemberInfo = shopMemberService.findMemberById(id);
        if (null == shopMemberInfo){
            return ResultUtil.success(ExceptionsEnum.MEMBER_IS_NOT_EXIST.name);
        }
        return ResultUtil.success(shopMemberInfo);
    }

    @ApiOperation(value = "根据id商户成员信息方法")
    @PostMapping("/delete/id")
    public ResultUtil deleteById(@RequestBody int id){
        String msg = shopMemberService.deleteMemberById(id);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "根据商户ID查询商户成员信息方法")
    @PostMapping("/find/shop/id")
    public ResultUtil findByShopId(@RequestBody int shopId){
        List<ShopMemberInfo> members = shopMemberService.findMemberByShopId(shopId);
        if (members.isEmpty()){
            return ResultUtil.success(ExceptionsEnum.MEMBER_IS_NOT_EXIST.name);
        }
        return ResultUtil.success(members);
    }
}
