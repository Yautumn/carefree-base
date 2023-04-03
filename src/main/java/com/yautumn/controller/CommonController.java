package com.yautumn.controller;

import com.yautumn.common.entity.shop.ShopCommodityInformation;
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

    @ApiOperation(value = "解析")
    @GetMapping("/save")
    public ResultUtil excelAnalysis(){
        ShopCommodityInformation shopCommodityInformation = new ShopCommodityInformation();
        shopCommodityInformation.setProductBrand("远东");
        shopCommodityInformation.setProductName("BV");
        shopCommodityInformation.setProductType("BV");
        shopCommodityInformation.setProductSpecific("2.5");
        shopCommodityInformation.setProductUnit("米");

        ShopCommodityInformation commodityInfo = new ShopCommodityInformation();
        commodityInfo.setProductBrand("远东");
        commodityInfo.setProductName("BV");
        commodityInfo.setProductType("BV");
        commodityInfo.setProductSpecific("2.5");
        commodityInfo.setProductUnit("米");

        return ResultUtil.success(commodityInfo.equals(shopCommodityInformation));
    }
}
