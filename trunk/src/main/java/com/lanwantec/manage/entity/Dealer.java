package com.lanwantec.manage.entity;

import java.util.Date;

/**
 * 经销商
 */
public class Dealer {
    private Integer dealerId; // int(4) unsigned NOT NULL AUTO_INCREMENT,
    private String name; // varchar(50) DEFAULT NULL COMMENT '名称',
    private String country; // varchar(15) DEFAULT NULL,
    private String province; // varchar(20) DEFAULT NULL,
    private String city; // varchar(20) DEFAULT NULL,
    private String address; // varchar(200) DEFAULT NULL,
    private Integer agentType; // tinyint(1) NOT NULL DEFAULT '0' COMMENT '经销商类型,默认0=独立经销商/厂商直营；1=厂商直营类型的代理商只能代理一个品牌',
    private String principal; // varchar(20) DEFAULT NULL COMMENT '负责人',
    private String contacter; // varchar(20) DEFAULT NULL COMMENT '联系人\n',
    private String contactPhone; // varchar(15) DEFAULT NULL COMMENT '联系电话',
    private String logoUrl; // varchar(200) DEFAULT '' COMMENT 'logo图Url',
    private Integer isValid; // tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否有效 1是有效 0是无效',
    private Date updateDate; // datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
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

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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
