package com.yautumn.common.entity.shop;

import lombok.Data;

@Data
public class ShopCargoProviderInfo {
    private int id;

    private int shopId;

    private String providerName;

    private String houseNumber;

    private String providerMarketId;

    private String providerAddr;

    private String status;

    private String createtime;

    private String updatetime;

    private String remark;

}