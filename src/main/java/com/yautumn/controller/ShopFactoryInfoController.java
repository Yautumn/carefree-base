package com.yautumn.controller;

import com.yautumn.common.entity.shop.ShopFactoryInfo;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.common.utils.ResultUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopFactoryInfoParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopFactoryInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "商户厂商接口")
@RestController
@RequestMapping("/shop/factory")
public class ShopFactoryInfoController {

    @Autowired
    private ShopFactoryInfoService shopFactoryInfoService;


    @ApiOperation(value = "添加商户厂商信息方法")
    @PostMapping("/save")
    public ResultUtil save(@RequestBody ShopFactoryInfoParam param){
        String msg = shopFactoryInfoService.save(param);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "删除商户厂商信息方法")
    @DeleteMapping("/delete")
    public  ResultUtil delete(@RequestParam int id){
        String msg = shopFactoryInfoService.del(id);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "根据id查询商户厂商信息方法")
    @GetMapping("/find/id")
    public  ResultUtil findById(@RequestParam int id){
        ShopFactoryInfo shopFactoryInfo = shopFactoryInfoService.findById(id);
        return ResultUtil.success(shopFactoryInfo);
    }

    @ApiOperation(value = "查询所有商户厂商方法")
    @PostMapping("/find/all")
    public  ResultUtil findAll(@RequestBody PageParam param){
        PageBeanUtil pageBeanUtil = shopFactoryInfoService.findAllByPage(param);
        if(pageBeanUtil.getList().isEmpty()){
            return ResultUtil.success(ExceptionsEnum.SHOP_FACTORY_IS_NOT_EXIST.name);
        }
        return ResultUtil.success(pageBeanUtil);
    }


    @ApiOperation(value = "更新商户厂商信息方法")
    @PostMapping("/update")
    public  ResultUtil update(@RequestBody ShopFactoryInfoParam param){
        String msg = shopFactoryInfoService.update(param);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "根据商户id查询商户厂商数量方法")
    @GetMapping("/count")
    public  ResultUtil count(@RequestParam int shopId){
        int count = shopFactoryInfoService.count(shopId);
        return ResultUtil.success(count);
    }

}
