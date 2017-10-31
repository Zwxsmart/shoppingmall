package com.lanwantec.manage.entity;

import java.util.Date;

/**
 * 设计方案基本信息
 */
public class Case {
    private Integer caseId; // int(4) unsigned NOT NULL AUTO_INCREMENT COMMENT '方案编号',
    private String title; // varchar(100) NOT NULL COMMENT '方案标题',
    private String designerNo; // DEFAULT NULL COMMENT '设计师的编号',
    private String picUrl; // NOT NULL COMMENT '方案图片路径',
    private Integer collections; // NOT NULL DEFAULT '0' COMMENT '被收藏数',
    private Integer praises; // NOT NULL DEFAULT '0' COMMENT '被点赞数',
    private Integer comments; // NOT NULL DEFAULT '0' COMMENT '被评论数',
    private String description; // varchar(500) DEFAULT '' COMMENT '方案描述',
    private Integer isValid; // tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否有效 1是有效，0是无效',
    private Date updateDate; // datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesignerNo() {
        return designerNo;
    }

    public void setDesignerNo(String designerNo) {
        this.designerNo = designerNo;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getCollections() {
        return collections;
    }

    public void setCollections(Integer collections) {
        this.collections = collections;
    }

    public Integer getPraises() {
        return praises;
    }

    public void setPraises(Integer praises) {
        this.praises = praises;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
