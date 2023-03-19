package com.yautumn.controller;

import com.yautumn.common.utils.ResultUtil;
import com.yautumn.service.shop.ShopCommodityInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Api(tags = "公共接口")
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ShopCommodityInfoService shopCommodityInfoService;

    @ApiOperation(value = "添加商品信息方法")
    @PostMapping("/save")
    public ResultUtil excelAnalysis(@RequestPart("file") MultipartFile file, @RequestParam int shopId){
        List<Map> mapList = shopCommodityInfoService.analysisExcel(file,shopId);
        return ResultUtil.success(mapList);
    }
}
