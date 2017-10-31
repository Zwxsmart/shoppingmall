package com.lanwantec.manage.entity;

import java.util.Date;

/**
 * 店铺to购物中心
 */
public class StoreToMall {
    private Integer id;// int(4) unsigned NOT NULL,
    private Integer maillId;// int(4) NOT NULL,
    private Integer storeId;// int(4) unsigned NOT NULL,
    private Integer floor;// tinyint(1) NOT NULL DEFAULT '1',
    private String doorplate;// varchar(20) NOT NULL COMMENT '门牌号',
    private Integer isValid;// tinyint(1) NOT NULL DEFAULT '1',
    private Date updateDate;// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaillId() {
        return maillId;
    }

    public void setMaillId(Integer maillId) {
        this.maillId = maillId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getDoorplate() {
        return doorplate;
    }

    public void setDoorplate(String doorplate) {
        this.doorplate = doorplate;
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
