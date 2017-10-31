package com.lanwantec.manage.entity;

import java.util.Date;

public class CaseGoods {
    private Integer id; // unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
    private Integer caseId; // int(4) unsigned NOT NULL COMMENT '方案编号',
    private Integer goodsId; // int(4) unsigned NOT NULL COMMENT '商品No.',
    private String brandNo; // varchar(9) NOT NULL COMMENT '品牌编号',
    private Integer sortIndex; // smallint(2) unsigned NOT NULL DEFAULT '1' COMMENT '商品在方案中排序序号',
    private Integer casePicId; // int(11) NOT NULL COMMENT '方案图片的Id',
    private String goodsShowTag; // varchar(50) DEFAULT '' COMMENT '商品在方案中显示的标签',
    private Integer isValid; // tinyint(1) NOT NULL DEFAULT '1' COMMENT '1是有效，0是无效',
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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Integer getCasePicId() {
        return casePicId;
    }

    public void setCasePicId(Integer casePicId) {
        this.casePicId = casePicId;
    }

    public String getGoodsShowTag() {
        return goodsShowTag;
    }

    public void setGoodsShowTag(String goodsShowTag) {
        this.goodsShowTag = goodsShowTag;
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
