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


    @Override
    public boolean equals(Object o){
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ShopCommodityInformation shopCommodityInformation = (ShopCommodityInformation) o;
        boolean productBrandChack = false;
        boolean productTypeChack = false;
        boolean productSpecificChack = false;

        if (this.productBrand == shopCommodityInformation.productBrand) {
            productBrandChack = true;
        }else if (this.productBrand != null && this.productBrand.equals(shopCommodityInformation.productBrand)){
            productBrandChack = true;
        }

        if (this.productType == shopCommodityInformation.productType) {
            productTypeChack = true;
        }else if (this.productType != null && this.productType.equals(shopCommodityInformation.productType)){
            productTypeChack = true;
        }

        if (this.productSpecific == shopCommodityInformation.productSpecific) {
            productSpecificChack = true;
        }else if (this.productSpecific != null && this.productSpecific.equals(shopCommodityInformation.productSpecific)){
            productSpecificChack = true;
        }

        if (productBrandChack && productTypeChack && productSpecificChack){
            return true;
        }

        return false;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = 31*result + (productBrand == null ? 0 : productBrand.hashCode());
        result = 31*result + (productType == null ? 0 : productType.hashCode());
        result = 31*result + (productSpecific == null ? 0 : productSpecific.hashCode());
        return result;
    }
}