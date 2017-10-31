package com.lanwantec.manage.entity;

/**
 * 关联第三方用户体系
 */
public class UserThird {
    private String userNo;// varchar(12) DEFAULT NULL,
    private String openId;// varchar(50) DEFAULT NULL,
    private Integer thirdType;// tinyint(1) DEFAULT NULL COMMENT '第三方平台类型',
    private String thirdTypeName;// varchar(50) DEFAULT NULL COMMENT '蒂三方平台名称',
    private Integer isValid;// tinyint(1) DEFAULT '1'

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getThirdType() {
        return thirdType;
    }

    public void setThirdType(Integer thirdType) {
        this.thirdType = thirdType;
    }

    public String getThirdTypeName() {
        return thirdTypeName;
    }

    public void setThirdTypeName(String thirdTypeName) {
        this.thirdTypeName = thirdTypeName;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
