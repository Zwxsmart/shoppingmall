package com.lanwantec.manage.entity;

import java.util.Date;

/**
 * 标签
 */
public class Tag {
    private Integer id;// int(4) unsigned NOT NULL AUTO_INCREMENT,
    private String tag;// varchar(20) DEFAULT NULL COMMENT '标签，方案的标签',
    private String tagType;// varchar(10) DEFAULT NULL COMMENT '标签类型,room、roomType、roomFloor、roomLayout、style、budget、sqares\n',
    private String tagCode;// varchar(4) DEFAULT NULL COMMENT '标签代号',
    private Integer isValid;// tinyint(1) DEFAULT NULL COMMENT '是否有效，默认1=有效，0=无效',
    private Date updateDate;// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
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
