package com.yautumn.param.request.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShopMemberParam {

    @ApiModelProperty(example = "1",value = "商户id")
    private int shopId;

    @ApiModelProperty(example = "XXX",value = "商户店员名称")
    private String assistantName;

    @ApiModelProperty(example = "1XXXXXXX",value = "商户店员电话")
    private String assistantPhone;

    @ApiModelProperty(example = "01",value = "商户店员类型(01 管理员、02 店员、03 仓库管理员)")
    private String assistantType;

    @ApiModelProperty(example = "备注",value = "备注")
    private String remark;
}
