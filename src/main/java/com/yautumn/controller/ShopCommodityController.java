package com.yautumn.controller;

import com.yautumn.common.entity.shop.ShopCommodityInformation;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.common.utils.ResultUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopCommodityParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.shop.ShopCommodityInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "店铺商品信息接口")
@RestController
@RequestMapping("shop/commodity")
public class ShopCommodityController {

    @Autowired
    private ShopCommodityInfoService shopCommodityInfoService;

    @ApiOperation(value = "添加商品信息方法")
    @PostMapping("/save")
    public ResultUtil saveCommodity(@RequestBody ShopCommodityParam shopCommodityParam){
        String msg = shopCommodityInfoService.insert(shopCommodityParam);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "根据id删除商品信息方法")
    @DeleteMapping("/delete/id")
    public ResultUtil delCommodity(@RequestParam @ApiParam(value = "商品id" , defaultValue = "1")int shopCommodityId){
        String msg = shopCommodityInfoService.delShopCommodityByID(shopCommodityId);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "更新商品信息方法")
    @PostMapping("/update")
    public ResultUtil updateCommodity(@RequestBody ShopCommodityParam shopCommodityParam){
        String msg = shopCommodityInfoService.updateCommdityByID(shopCommodityParam);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "根据id查询商品信息方法")
    @GetMapping("/find/id")
    public ResultUtil findShopById(@RequestParam @ApiParam(value = "商品id" , defaultValue = "1")int shopCommodityId){
        ShopCommodityInformation shopCommodityInformation = shopCommodityInfoService.findShopCommodityByID(shopCommodityId);
        if (null != shopCommodityInformation){
            return ResultUtil.success(shopCommodityInformation);
        }else {
            return ResultUtil.success(ExceptionsEnum.SHOP_IS_NOT_EXIST.name);
        }
    }

    @ApiOperation(value = "查询商品数量方法")
    @GetMapping("/count")
    public ResultUtil countCommoditys(int shopId){
        int count = 0;
        count = shopCommodityInfoService.countCommoditys(shopId);
        return ResultUtil.success(count);
    }


    @ApiOperation(value = "分页查询商品信息方法")
    @PostMapping("/find/all")
    public ResultUtil findCommodityAll(@RequestBody PageParam pageParam){
        PageBeanUtil pageBeanUtil = shopCommodityInfoService.findCommdityAll(pageParam);
        return ResultUtil.success(pageBeanUtil);
    }

    @ApiOperation(value = "批量添加商品信息方法")
    @PostMapping("/batch/insert")
    public ResultUtil batchInsert(@RequestBody List<ShopCommodityParam> shopCommodityParams){
        String msg = shopCommodityInfoService.batchInsert(shopCommodityParams);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "解析")
    @PostMapping("/analysis")
    public ResultUtil excelAnalysis(@RequestPart("file") MultipartFile file, @RequestParam int shopId){
        String msg = shopCommodityInfoService.analysisExcel(file,shopId);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "批量更新商品信息方法")
    @PostMapping("/batch/update")
    public ResultUtil batchUpdate(@RequestBody List<ShopCommodityParam> shopCommodityParams){
        String msg = shopCommodityInfoService.batchUpdate(shopCommodityParams);
        return ResultUtil.success(msg);
    }
}
