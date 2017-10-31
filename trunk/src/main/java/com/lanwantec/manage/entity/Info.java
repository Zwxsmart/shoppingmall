package com.lanwantec.manage.entity;

import java.util.Date;

/**
 * 资讯表（新闻、简介等）
 */
public class Info {
    private Integer id;// int(10) unsigned NOT NULL AUTO_INCREMENT,
    private String title;// varchar(100) NOT NULL,
    private String picTitle;// varchar(255) DEFAULT NULL COMMENT '图片标题链接',
    private String showPicTitle;// varchar(255) DEFAULT NULL COMMENT '图片标题链接',
    private Date createDate;// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    private String content;// text COMMENT '内容',
    private Integer infoType;// tinyint(2) NOT NULL DEFAULT '0' COMMENT '咨询类型，0=新闻，1=简介等',
    private String infoTypeName;// varchar(50) NOT NULL DEFAULT '新闻' COMMENT '咨询类型名称',
    private String operNo;// varchar(10) NOT NULL DEFAULT '0000' COMMENT '操作者编号',
    private String operName;// varchar(50) NOT NULL DEFAULT '系统' COMMENT '操作者名称',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicTitle() {
        return picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public String getInfoTypeName() {
        return infoTypeName;
    }

    public void setInfoTypeName(String infoTypeName) {
        this.infoTypeName = infoTypeName;
    }

    public String getOperNo() {
        return operNo;
    }

    public void setOperNo(String operNo) {
        this.operNo = operNo;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getShowPicTitle() {
        return showPicTitle;
    }

    public void setShowPicTitle(String showPicTitle) {
        this.showPicTitle = showPicTitle;
    }
}
