package com.lanwantec.manage.entity;

import java.util.Date;

/**
 * 品牌to门店表
 */
public class BrandToStore {
    private Integer id; //unsigned NOT NULL AUTO_INCREMENT,
    private String brandNo; // NOT NULL COMMENT '品牌编号'
    private Integer storeId; // NOT NULL COMMENT '商店编号'
    private Integer isValid; // tinyint(1) NOT NULL DEFAULT '1' COMMENT '(默认1=有效，0=无效)',
    private Date updateDate; // datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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
