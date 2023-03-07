package com.yautumn.common.entity.shop;

import lombok.Data;

@Data
public class ShopMemberInfo {
    private int id;

    private int shopId;

    private String assistantName;

    private String assistantPhone;

    private String assistantType;

    private String status;

    private String createtime;

    private String updatetime;

    private String remark;

}