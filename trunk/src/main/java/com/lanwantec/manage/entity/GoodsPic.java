package com.lanwantec.manage.entity;

import java.util.Date;

/**
 * 商品图片
 */
public class GoodsPic {
    private Integer id;// int(4) unsigned NOT NULL AUTO_INCREMENT,
    private Integer goodsId;// int(4) unsigned NOT NULL COMMENT '商品编号',
    private String url;// varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '大图URL',
    private String description;// varchar(100) DEFAULT '' COMMENT '图片描述',
    private Integer sortIndex;// tinyint(2) NOT NULL DEFAULT '0' COMMENT '图片显示顺序',
    private Integer isValid;// tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否有效 1是有效 0是无效',
    private Date updateDate;// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
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
