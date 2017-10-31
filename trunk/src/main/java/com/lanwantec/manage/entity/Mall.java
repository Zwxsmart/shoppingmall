package com.lanwantec.manage.entity;

import java.util.Date;

/**
 * 购物中心
 */
public class Mall {
    private Integer mallId;// int(4) unsigned NOT NULL AUTO_INCREMENT,
    private String name;// varchar(50) NOT NULL,
    private String country;// varchar(15) NOT NULL,
    private String province;// varchar(20) NOT NULL DEFAULT '' COMMENT '省份',
    private String city;// varchar(20) NOT NULL DEFAULT '' COMMENT '城市',
    private String address;// varchar(200) NOT NULL COMMENT '详细地址',
    private String longitude;// varchar(20) NOT NULL DEFAULT '0' COMMENT '经度',
    private String latitude;// varchar(20) NOT NULL DEFAULT '0' COMMENT '纬度',
    private Integer isValid;// tinyint(1) NOT NULL DEFAULT '1',
    private Date updateDate;// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',

    public Integer getMallId() {
        return mallId;
    }

    public void setMallId(Integer mallId) {
        this.mallId = mallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
