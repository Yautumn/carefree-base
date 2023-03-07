package com.yautumn.common.entity.shop;

import lombok.Data;

@Data
public class ShopAgentInfo {
    private int id;

    private int shopId;

    private String agentName;

    private String agentPhone;

    private String agentMobile;

    private String agentCity;

    private String agentAddr;

    private String agentLogistics;

    private String status;

    private String createtime;

    private String updatetime;

    private String remark;

}