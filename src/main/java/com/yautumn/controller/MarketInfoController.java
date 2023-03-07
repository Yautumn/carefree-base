package com.yautumn.controller;

import com.yautumn.common.entity.market.MarketInfo;
import com.yautumn.common.utils.PageBeanUtil;
import com.yautumn.common.utils.ResultUtil;
import com.yautumn.param.request.common.PageParam;
import com.yautumn.param.request.market.MarketParam;
import com.yautumn.param.response.ExceptionsEnum;
import com.yautumn.service.market.MarketInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "市场信息接口")
@RestController
@RequestMapping("/market")
public class MarketInfoController {

    @Autowired
    private MarketInfoService marketInfoService;

    @ApiOperation(value = "新增市场方法")
    @PostMapping("/add")
    public ResultUtil insert(@RequestBody MarketParam marketParam){
        String msg = marketInfoService.insert(marketParam);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "根据市场id删除市场方法")
    @DeleteMapping("/del/id")
    public ResultUtil del(@RequestParam @ApiParam(value = "市场id" , defaultValue = "1") Integer marketId){
        String msg = marketInfoService.delete(marketId);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "更新市场方法")
    @PostMapping("/update")
    public ResultUtil update(@RequestBody MarketParam marketParam){
        String msg = marketInfoService.update(marketParam);
        return ResultUtil.success(msg);
    }

    @ApiOperation(value = "根据id查询市场方法")
    @GetMapping("/find/id")
    public ResultUtil findById(@RequestParam @ApiParam(value = "市场id" , defaultValue = "1") Integer marketId){
        MarketInfo marketInfo = marketInfoService.findById(marketId);
        if(null == marketInfo){
            return ResultUtil.success(ExceptionsEnum.MARKET_IS_NOT_EXIST.name);
        }
        return ResultUtil.success(marketInfo);
    }

    @ApiOperation(value = "查询所有市场方法")
    @PostMapping("/find/all")
    public ResultUtil findAll(@RequestBody PageParam pageParam){
        PageBeanUtil<MarketInfo> marketInfos = marketInfoService.findMarketAll(pageParam);
        if (marketInfos.getList().isEmpty()){
            ResultUtil.success(ExceptionsEnum.MARKET_IS_NOT_EXIST.name);
        }
        return ResultUtil.success(marketInfos);
    }

    @ApiOperation(value = "查询所有市场数量方法")
    @GetMapping("/count")
    public ResultUtil findAllCount(){
        int count = marketInfoService.countMarket();
        return ResultUtil.success(count);
    }

}
