package com.yautumn.controller;

import com.yautumn.common.entity.shop.ShopAgentInfo;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.common.utils.ResultUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.shop.ShopAgentParam;
import com.yautumn.service.shop.ShopAgentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "销售代理商接口")
@RestController
@RequestMapping("/shop/agent")
public class ShopAgentController {

    @Autowired
    private ShopAgentService service;

    @ApiOperation(value = "根据id查询销售代理商方法")
    @GetMapping("/find/id")
    public ResultUtil findById(@RequestParam int id){
        ShopAgentInfo shopAgentInfo = service.findById(id);
        return ResultUtil.success(shopAgentInfo);
    }

    @ApiOperation(value = "根据shopId查询销售代理商方法")
    @PostMapping("/find/all")
    public ResultUtil findAll(@RequestBody PageParam param, @RequestParam int shopId){
        PageBeanUtil pageBeanUtil = service.findAll(param,shopId);
        return ResultUtil.success(pageBeanUtil);
    }

    @ApiOperation(value = "根据id删除销售代理商方法")
    @DeleteMapping("/delete/id")
    public ResultUtil deleteById(@RequestParam int id){
        String msg = service.delete(id);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "新增销售代理商方法")
    @PostMapping("/save")
    public ResultUtil save(@RequestBody ShopAgentParam param){
        String msg = service.save(param);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "更新销售代理商方法")
    @PostMapping("/update")
    public ResultUtil update(@RequestBody ShopAgentParam param){
        String msg = service.update(param);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "查询销售代理商数量方法")
    @GetMapping("/count")
    public ResultUtil count(@RequestParam int shopId){
        int count = service.count(shopId);
        return ResultUtil.success(count);
    }
}
