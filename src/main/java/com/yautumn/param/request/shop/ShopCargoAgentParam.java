package com.yautumn.param.request.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShopCargoAgentParam {

    @ApiModelProperty(example = "1",value = "id")
    private int id;

    @ApiModelProperty(example = "1",value = "商户id")
    private int shopId;

    @ApiModelProperty(example = "XXXX",value = "代理商名称")
    private String agentName;

    @ApiModelProperty(example = "010-******",value = "代理商电话")
    private String agentMobile;

    @ApiModelProperty(example = "3-54-4",value = "门牌号")
    private String houseNumber;

    @ApiModelProperty(example = "1",value = "市场id")
    private String marketId;

    @ApiModelProperty(example = "XXXX",value = "代理商地址")
    private String agentAddr;

    @ApiModelProperty(example = "备注",value = "备注")
    private String remark;
}
