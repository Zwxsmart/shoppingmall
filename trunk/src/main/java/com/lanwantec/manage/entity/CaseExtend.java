package com.lanwantec.manage.entity;

import java.util.Date;

/**
 * 场景方案扩展属性
 */
public class CaseExtend {
    private Integer id; // unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
    private Integer caseId; // int(4) unsigned NOT NULL AUTO_INCREMENT COMMENT '方案编号',
    private String caseTypeField; // varchar(50) NOT NULL COMMENT 'style风格、theme主题、area区域、part局部',
    private String caseTypeNo; // varchar(10) NOT NULL COMMENT '属性编号',
    private String caseType; // varchar(50) NOT NULL COMMENT '属性值',
    private Integer isCrosswise; // tinyint(1) NOT NULL DEFAULT '0' COMMENT '1是横图，0是竖图',
    private Integer isValid; // tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否有效 1有效 0无效',
    private Date updateDate; // datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getCaseTypeField() {
        return caseTypeField;
    }

    public void setCaseTypeField(String caseTypeField) {
        this.caseTypeField = caseTypeField;
    }

    public String getCaseTypeNo() {
        return caseTypeNo;
    }

    public void setCaseTypeNo(String caseTypeNo) {
        this.caseTypeNo = caseTypeNo;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public Integer getIsCrosswise() {
        return isCrosswise;
    }

    public void setIsCrosswise(Integer isCrosswise) {
        this.isCrosswise = isCrosswise;
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
