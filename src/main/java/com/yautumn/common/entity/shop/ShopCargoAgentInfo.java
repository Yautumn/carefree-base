package com.yautumn.common.entity.shop;

import lombok.Data;

@Data
public class ShopCargoAgentInfo {
    private int id;

    private int shopId;

    private String agentName;

    private String agentMobile;

    private String houseNumber;

    private String marketId;

    private String agentAddr;

    private String status;

    private String createtime;

    private String updatetime;

    private String remark;
}