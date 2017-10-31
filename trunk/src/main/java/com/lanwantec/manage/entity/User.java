package com.lanwantec.manage.entity;

import java.util.Date;

/**
 * 用户
 */
public class User {
    private String userNo;// varchar(14) NOT NULL COMMENT '用户编号',
    private String name;// varchar(20) DEFAULT NULL COMMENT '姓名',
    private String nick;// varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称（15个中文字符或表情，30个英文字符）',
    private String phone;// char(11) NOT NULL DEFAULT '' COMMENT '手机号',
    private String gender;// varchar(2) DEFAULT NULL COMMENT '性别，男=M,女=F',
    private String country;// varchar(15) DEFAULT NULL,
    private String birth;// varchar(8) DEFAULT NULL COMMENT '出生日期',
    private String province;// varchar(20) DEFAULT NULL COMMENT '省、州、区',
    private String city;// varchar(20) DEFAULT NULL COMMENT '城市',
    private Date createDate;// datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    private Integer isValid;// tinyint(1) NOT NULL DEFAULT '1',
    private String wxOpenId;// varchar(50) DEFAULT NULL COMMENT '微信个人账号的OpenId',
    private Integer code;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
