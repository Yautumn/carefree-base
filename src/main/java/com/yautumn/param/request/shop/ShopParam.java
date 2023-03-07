package com.yautumn.param.request.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopParam {

    @ApiModelProperty(example = "1",value = "商户id")
    private int id;

    @ApiModelProperty(example = "商户名称1",value = "商户名称")
    private String name;

    @ApiModelProperty(example = "商店1",value = "商户别名")
    private String alias;

    @ApiModelProperty(example = "001",value = "商户所处市场id")
    private int marketId;

    @ApiModelProperty(example = "9-a-64",value = "门牌号")
    private String houseNumber;

    @ApiModelProperty(example = "第一街64号",value = "商户地址")
    private String addr;

    @ApiModelProperty(example = "材料1",value = "商户标签（主营产品）")
    private String tag;

    @ApiModelProperty(example = "XXX",value = "商户店主")
    private String keeper;

    @ApiModelProperty(example = "010-XXXX",value = "商户电话1")
    private String phone1;

    @ApiModelProperty(example = "010-XXXX",value = "商户电话2")
    private String phone2;

    @ApiModelProperty(example = "1******",value = "商户手机1")
    private String mobile1;

    @ApiModelProperty(example = "1******",value = "商户手机2")
    private String mobile2;

    @ApiModelProperty(example = "备注",value = "备注")
    private String remark;

}
