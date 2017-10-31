package com.lanwantec.manage.entity;

import java.util.Date;

/**
 *  品牌基础表
 */
public class Brand {
    private String brandNo; // NOT NULL COMMENT '品牌编号'
    private String name; // NOT NULL COMMENT '品牌名称\n'
    private String abbreviation; // DEFAULT 'Z' COMMENT '拼音缩写',
    private String country; // NOT NULL DEFAULT '中国' COMMENT '国家',
    private String province; // NOT NULL COMMENT '所在省\\州\\区',
    private String city; // NNOT NULL COMMENT '所属城市',
    private String address; // NOT NULL DEFAULT '' COMMENT '详细地址',
    private String principal; // DEFAULT '' COMMENT '负责人',
    private String contacter; // varchar(20) DEFAULT '' COMMENT '联系人\n',
    private String contactPhone; // DEFAULT NULL COMMENT '联系电话',
    private String intro; // DEFAULT '' COMMENT '品牌简介',
    private String logoUrl; // NOT NULL COMMENT 'logo图Url',
    private String qrCodeUrl; // varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '品牌微信二维码路径',
    private Integer isValid; // tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否有效，1是有效，0是无效',
    private Date updateDate; // datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',

    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
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
