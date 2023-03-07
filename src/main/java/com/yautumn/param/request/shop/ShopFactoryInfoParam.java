package com.yautumn.param.request.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShopFactoryInfoParam {

    @ApiModelProperty(example = "1",value = "id")
    private int id;
    @ApiModelProperty(example = "1",value = "商户id")
    private int shopId;

    @ApiModelProperty(example = "XXX",value = "商户厂商名称")
    private String factoryName;

    @ApiModelProperty(example = "XXX省XXXX市XXXX区XXXX",value = "商户厂商地址")

    private String factoryAddr;

    @ApiModelProperty(example = "010-********",value = "商户厂商电话1")

    private String factoryPhone1;

    @ApiModelProperty(example = "010-********",value = "商户厂商电话2")

    private String factoryPhone2;

    @ApiModelProperty(example = "1*******",value = "商户厂商手机1")

    private String factoryMobile1;

    @ApiModelProperty(example = "1********",value = "商户厂商手机1")
    private String factoryMobile2;

    @ApiModelProperty(example = "备注",value = "备注")
    private String remark;
}
