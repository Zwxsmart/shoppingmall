package com.lanwantec.manage.entity;

import java.util.Date;

/**
 * 单品表
 */
public class Goods {
    private Integer goodsId; // int(4) unsigned NOT NULL AUTO_INCREMENT,
    private String goodsName; // varchar(80) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '单品名称',
    private String brandNo; // varchar(9) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '品牌编号',
    private String unit; // varchar(6) CHARACTER SET utf8 NOT NULL COMMENT '计量单位',
    private Integer length; // int(10) unsigned DEFAULT '0' COMMENT '长',
    private Integer width; // int(10) unsigned DEFAULT '0' COMMENT '宽',
    private Integer height; // int(10) unsigned DEFAULT '0' COMMENT '高',
    private String specification; // varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '规格',
    private String color; // varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '颜色',
    private String categoryNo; // varchar(6) CHARACTER SET utf8 NOT NULL COMMENT '商品分类编号,关联tbl_GoodsCategory',
    private String category; // varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类',
    private String materialNo; // varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '商品材质编号,关联tbl_GoodsMaterial',
    private String  material; // varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '材质',
    private String styleNo; // varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '风格编号,关联tbl_GoodsStyle',
    private String style; // varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '风格',
    private String originGoodsNo; // varchar(40) CHARACTER SET utf8 DEFAULT NULL COMMENT '厂家货号',
    private String description; // varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '产品描述',
    private String other; // varchar(500) CHARACTER SET utf8 DEFAULT '' COMMENT '产品扩展描述字段（不做搜索用，只为显示）（格式用json)',
    private String creator; // varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改者',
    private String verifier; // varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核者',
    private Date createDate; // datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'c创建日期',
    private Date approvalDate; // datetime DEFAULT NULL COMMENT '审核日期',
    private String showTag; // varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片中显示的标签',
    private String smallUrl; // varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '存列表小图url',
    private String whiteSmallUrl; // varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '白底图',
    private Integer isCrosswise; // tinyint(1) DEFAULT '0' COMMENT '1是横图，0是方图',
    private Integer isValid; // tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否有效 1有效 0无效',
    private Date updateDate; // datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getStyleNo() {
        return styleNo;
    }

    public void setStyleNo(String styleNo) {
        this.styleNo = styleNo;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getOriginGoodsNo() {
        return originGoodsNo;
    }

    public void setOriginGoodsNo(String originGoodsNo) {
        this.originGoodsNo = originGoodsNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getShowTag() {
        return showTag;
    }

    public void setShowTag(String showTag) {
        this.showTag = showTag;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getWhiteSmallUrl() {
        return whiteSmallUrl;
    }

    public void setWhiteSmallUrl(String whiteSmallUrl) {
        this.whiteSmallUrl = whiteSmallUrl;
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
