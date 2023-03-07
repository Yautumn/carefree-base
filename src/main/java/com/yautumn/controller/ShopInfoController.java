package com.yautumn.controller;

import com.yautumn.common.entity.shop.ShopInfo;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.common.utils.ResultUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "商户信息接口")
@RestController
@RequestMapping("/shop")
public class ShopInfoController {

    @Autowired
    private ShopInfoService shopInfoService;

    @ApiOperation(value = "添加商户信息方法")
    @PostMapping("/save")
    public ResultUtil saveShopInfo(@RequestBody ShopParam shopParam) {
        String message = shopInfoService.insert(shopParam);
        return ResultUtil.success(message);
    }

    @ApiOperation(value = "根据商户id删除商户信息方法")
    @DeleteMapping("/del/id")
    public ResultUtil delShopInfo(@RequestParam @ApiParam(value = "商户id" , defaultValue = "1") int shopId) {
        String msg = shopInfoService.delShopByID(shopId);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "更新单个商户信息方法")
    @PostMapping("/update")
    public ResultUtil updateShop(@RequestBody ShopParam shopParam) {

        String msg = shopInfoService.updateShop(shopParam);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "根据id查询商户信息方法")
    @GetMapping("/find/id")
    public ResultUtil findShopById(@RequestParam @ApiParam(value = "商户id", defaultValue = "1") int shopId) {
        ShopInfo shopInfo = shopInfoService.findShopById(shopId);
        if (null != shopInfo) {
            return ResultUtil.success(shopInfo);
        } else {
            return ResultUtil.success(ExceptionsEnum.SHOP_IS_NOT_EXIST.name);
        }
    }

    @ApiOperation(value = "获取商户总数方法")
    @GetMapping("/count")
    public ResultUtil countShops() {
        int count = 0;
        count = shopInfoService.countShop();
        return ResultUtil.success(count);
    }

    @ApiOperation(value = "分页查询所有商户信息方法")
    @PostMapping("/find/all")
    public ResultUtil findShopAll(@RequestBody PageParam pageParam) {
        PageBeanUtil pageBeanUtil = shopInfoService.findShopAll(pageParam);
        if(pageBeanUtil.getList().isEmpty()){
            return ResultUtil.success(ExceptionsEnum.SHOP_IS_NOT_EXIST.name);
        }
        return ResultUtil.success(pageBeanUtil);
    }

}
