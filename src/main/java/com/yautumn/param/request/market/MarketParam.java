package com.yautumn.param.request.market;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketParam {
    @ApiModelProperty(example = "1",value = "市场id")
    private Integer id;

    @ApiModelProperty(example = "XJSC",value = "市场名称")
    private String marketName;

    @ApiModelProperty(example = "河南省",value = "市场所在省份")
    private String marketProvince;

    @ApiModelProperty(example = "郑州市",value = "市场所处城市")
    private String marketCity;

    @ApiModelProperty(example = "XX区XXX路XXX号XXX市场",value = "市场地址")
    private String marketAddr;

    @ApiModelProperty(example = "备注",value = "备注")
    private String remark;
}