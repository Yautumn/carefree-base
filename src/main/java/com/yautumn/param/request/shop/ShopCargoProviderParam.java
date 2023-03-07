package com.yautumn.param.request.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShopCargoProviderParam {

    @ApiModelProperty(example = "1",value = "商户id")
    private int shopId;

    @ApiModelProperty(example = "XXXX",value = "商户调货供应商名称")
    private String providerName;

    @ApiModelProperty(example = "32",value = "门牌号")
    private String houseNumber;

    @ApiModelProperty(example = "1",value = "商户调货供应商所属市场id")
    private String providerMarketId;

    @ApiModelProperty(example = "XXXX",value = "商户调货供应商地址")
    private String providerAddr;

    @ApiModelProperty(example = "备注",value = "备注")
    private String remark;
}
