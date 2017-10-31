package com.lanwantec.manage.entity;

/**
 * 商品类别
 */
public class GoodsCategory {
    private String goodsCategoryNo;// varchar(6) NOT NULL COMMENT '家具分类编号，第一层2位，第二层2位，第三层2位',
    private String  categoryName;// varchar(30) NOT NULL,
    private String description;// varchar(100) DEFAULT NULL COMMENT '描述',
    private Integer isSub;// tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是子级，0不是，1是',
    private Integer isValid;// tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，0无效，1有效',

    public String getGoodsCategoryNo() {
        return goodsCategoryNo;
    }

    public void setGoodsCategoryNo(String goodsCategoryNo) {
        this.goodsCategoryNo = goodsCategoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsSub() {
        return isSub;
    }

    public void setIsSub(Integer isSub) {
        this.isSub = isSub;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
