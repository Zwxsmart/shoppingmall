package com.lanwantec.manage.entity;

import java.util.Date;

/**
 * 门店j基本信息
 */
public class Store {
    private Integer storeId;// int(4) unsigned NOT NULL AUTO_INCREMENT,
    private String name;// varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商店名称',
    private Integer dealerId;// int(4) NOT NULL COMMENT '经销商编号',
    private String country;// varchar(15) NOT NULL COMMENT '国家',
    private String province;// varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '省份',
    private String city;// varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '城市',
    private String address;// varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '详细地址',
    private String principal;// varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '门店负责人',
    private String contacter;// varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '门店联系人\n',
    private String contactPhone;// varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '联系电话',
    private String storePhone;// varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '门店电话',
    private String longitude;// varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '0' COMMENT '经度',
    private String latitude;// varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '0' COMMENT '纬度',
    private String showUrl;// varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '门店展示图地址',
    private String showUrl1;// varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '门店展示图地址',
    private Integer isValid;// tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否有效 1是有效 0是无效',
    private String openingHours;// varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '营业时间',
    private String provideService;// varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '提供服务',
    private Date updateDate;// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
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

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getProvideService() {
        return provideService;
    }

    public void setProvideService(String provideService) {
        this.provideService = provideService;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getShowUrl1() {
        return showUrl1;
    }

    public void setShowUrl1(String showUrl1) {
        this.showUrl1 = showUrl1;
    }
}
