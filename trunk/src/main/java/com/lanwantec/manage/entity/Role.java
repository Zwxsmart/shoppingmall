package com.lanwantec.manage.entity;

/**
 * 角色
 */
public class Role {
    private Integer id;// int(4) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
    private String role;// varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
    private String description;// varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色描述',
    private Integer isValid;// tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用  ：1可用 ，0失效',
    private Integer roleType;// int(4) DEFAULT '1' COMMENT '角色类型0是内置角色，1是用户可操作角色。',
    private Integer roleIdentity;// tinyint(3) DEFAULT '0' COMMENT '角色身份类型，0是厂家，1是经销商，2门店',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getRoleIdentity() {
        return roleIdentity;
    }

    public void setRoleIdentity(Integer roleIdentity) {
        this.roleIdentity = roleIdentity;
    }
}
