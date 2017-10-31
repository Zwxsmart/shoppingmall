package com.lanwantec.manage.entity;

/**
 * 商品材质
 */
public class GoodsMaterial {
    private String goodsMaterialNo;// varchar(6) NOT NULL,
    private String materialName;// varchar(30) NOT NULL,
    private Integer isValid;// tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效,默认1=有效,0=无效',
    private Integer isSub;// tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是子级，0不是，1是',
    private String description;// varchar(100) DEFAULT NULL COMMENT '描述',

    public String getGoodsMaterialNo() {
        return goodsMaterialNo;
    }

    public void setGoodsMaterialNo(String goodsMaterialNo) {
        this.goodsMaterialNo = goodsMaterialNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsSub() {
        return isSub;
    }

    public void setIsSub(Integer isSub) {
        this.isSub = isSub;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
