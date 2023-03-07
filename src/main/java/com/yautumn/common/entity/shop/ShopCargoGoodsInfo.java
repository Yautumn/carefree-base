package com.yautumn.common.entity.shop;

import lombok.Data;

@Data
public class ShopCargoGoodsInfo {
    private int id;

    private int shopId;

    private String productBrand;

    private String cargoGoodsName;

    private String cargoGoodsType;

    private String cargoGoodsSpecific;

    private String cargoGoodsUnit;

    private String status;

    private String createtime;

    private String updatetime;

    private String remark;

}