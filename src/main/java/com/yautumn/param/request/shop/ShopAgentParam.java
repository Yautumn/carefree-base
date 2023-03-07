package com.yautumn.param.request.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShopAgentParam {

    @ApiModelProperty(example = "1",value = "id")
    private int id;

    @ApiModelProperty(example = "1",value = "商户id")
    private int shopId;

    @ApiModelProperty(example = "XXXX",value = "代理商名称")
    private String agentName;

    @ApiModelProperty(example = "010-******",value = "代理商电话")
    private String agentPhone;

    @ApiModelProperty(example = "1******",value = "代理商手机号")
    private String agentMobile;

    @ApiModelProperty(example = "XXXX",value = "代理商所在城市")
    private String agentCity;

    @ApiModelProperty(example = "XXXX",value = "代理商地址")
    private String agentAddr;

    @ApiModelProperty(example = "XXXX",value = "代理商约定物流")
    private String agentLogistics;

    @ApiModelProperty(example = "备注",value = "备注")
    private String remark;
}
