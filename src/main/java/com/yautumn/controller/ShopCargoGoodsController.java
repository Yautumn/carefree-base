package com.yautumn.controller;

import com.yautumn.common.entity.shop.ShopCargoGoodsInfo;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.common.utils.ResultUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopCargoGoodsParam;
import com.yautumn.service.shop.ShopCargoGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商户调货商品接口")
@RestController
@RequestMapping("/shop/cargo/goods")
public class ShopCargoGoodsController {

    @Autowired
    private ShopCargoGoodsService service;

    @ApiOperation(value = "根据id查询调货商品方法")
    @GetMapping("/find/id")
    public ResultUtil findById(@RequestParam int id){
        ShopCargoGoodsInfo shopAgentInfo = service.findById(id);
        return ResultUtil.success(shopAgentInfo);
    }

    @ApiOperation(value = "根据shopId查询调货商品方法")
    @PostMapping("/find/all")
    public ResultUtil findAll(@RequestBody PageParam param, @RequestParam int shopId){
        PageBeanUtil pageBeanUtil = service.findAll(param,shopId);
        return ResultUtil.success(pageBeanUtil);
    }

    @ApiOperation(value = "根据id删除调货商品方法")
    @DeleteMapping("/delete/id")
    public ResultUtil deleteById(@RequestParam int id){
        String msg = service.delete(id);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "新增调货商品方法")
    @PostMapping("/save")
    public ResultUtil save(@RequestBody ShopCargoGoodsParam param){
        String msg = service.save(param);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "更新调货商品方法")
    @PostMapping("/update")
    public ResultUtil update(@RequestBody ShopCargoGoodsParam param){
        String msg = service.update(param);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "查询调货商品数量方法")
    @GetMapping("/count")
    public ResultUtil count(@RequestParam int shopId){
        int count = service.count(shopId);
        return ResultUtil.success(count);
    }

    @ApiOperation(value = "批量添加商品信息方法")
    @PostMapping("/batch/insert")
    public ResultUtil batchInsert(@RequestBody List<ShopCargoGoodsParam> params){
        String msg = service.batchInsert(params);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "批量更新商品信息方法")
    @PostMapping("/batch/update")
    public ResultUtil batchUpdate(@RequestBody List<ShopCargoGoodsParam> params){
        String msg = service.batchUpdate(params);
        return ResultUtil.success(msg);
    }
}
