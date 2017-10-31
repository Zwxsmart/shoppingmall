package com.lanwantec.manage.entity;

/**
 * 商品风格
 */
public class GoodsStyle {
    private String goodsStyleNo;// varchar(6) NOT NULL COMMENT '风格编号',
    private String styleName;// varchar(30) NOT NULL COMMENT '风格名称',
    private Integer isValid;// tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，默认1=有效，0=无效',
    private Integer isSub;// tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是子级，0不是，1是',
    private String description;// varchar(100) DEFAULT NULL COMMENT '描述',

    public String getGoodsStyleNo() {
        return goodsStyleNo;
    }

    public void setGoodsStyleNo(String goodsStyleNo) {
        this.goodsStyleNo = goodsStyleNo;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
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
