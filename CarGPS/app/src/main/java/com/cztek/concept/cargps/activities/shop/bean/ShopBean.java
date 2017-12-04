package com.cztek.concept.cargps.activities.shop.bean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/4
 */

public class ShopBean {
    private String id;
    private String name;
    private String fullName;
    private String address;
    private String longitude;
    private String latitude;
    private String allBankPath;
    private String shopType;
    private String parentId;
    private Boolean isSelected;
    private List<ShopBean> ChildShops;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAllBankPath() {
        return allBankPath;
    }

    public void setAllBankPath(String allBankPath) {
        this.allBankPath = allBankPath;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsSelected() {
        return isSelected == null ? true : this.isSelected;
    }

    public void setIsSelected(Boolean selected) {
        this.isSelected = selected;
    }

    public List<ShopBean> getChildShops() {
        return ChildShops;
    }

    public void setChildShops(List<ShopBean> childShops) {
        ChildShops = childShops;
    }
}
