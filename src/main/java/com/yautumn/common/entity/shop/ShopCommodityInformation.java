package com.yautumn.common.entity.shop;

import lombok.Data;

@Data
public class ShopCommodityInformation {
    private int id;

    private int shopId;

    private String productBrand;

    private String productName;

    private String productType;

    private String productSpecific;

    private String productUnit;

    private String status;

    private String createtime;

    private String updatetime;

    private String remark;

}