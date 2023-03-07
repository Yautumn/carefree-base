package com.yautumn.controller;

import com.yautumn.common.entity.shop.ShopCargoAgentInfo;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.common.utils.ResultUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopCargoAgentParam;
import com.yautumn.service.shop.ShopCargoAgentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "商户调货销售商接口")
@RestController
@RequestMapping("/shop/cargo/agent")
public class ShopCargoAgentController {

    @Autowired
    private ShopCargoAgentService service;

    @ApiOperation(value = "根据id查询调货销售商方法")
    @GetMapping("/find/id")
    public ResultUtil findById(@RequestParam int id){
        ShopCargoAgentInfo shopCargoAgentInfo = service.findById(id);
        return ResultUtil.success(shopCargoAgentInfo);
    }

    @ApiOperation(value = "根据shopId查询调货销售商方法")
    @PostMapping("/find/all")
    public ResultUtil findAll(@RequestBody PageParam param, @RequestParam int shopId){
        PageBeanUtil pageBeanUtil = service.findAll(param,shopId);
        return ResultUtil.success(pageBeanUtil);
    }

    @ApiOperation(value = "根据id删除调货销售商方法")
    @DeleteMapping("/delete/id")
    public ResultUtil deleteById(@RequestParam int id){
        String msg = service.delete(id);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "新增调货销售商方法")
    @PostMapping("/save")
    public ResultUtil save(@RequestBody ShopCargoAgentParam param){
        String msg = service.save(param);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "更新调货销售商方法")
    @PostMapping("/update")
    public ResultUtil update(@RequestBody ShopCargoAgentParam param){
        String msg = service.update(param);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "查询调货销售商数量方法")
    @GetMapping("/count")
    public ResultUtil count(@RequestParam int shopId){
        int count = service.count(shopId);
        return ResultUtil.success(count);
    }

}
