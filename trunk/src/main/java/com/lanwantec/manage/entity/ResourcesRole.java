package com.lanwantec.manage.entity;

/**
 * 资源角色对应关系
 */
public class ResourcesRole {
    private Integer id;// int(4) unsigned NOT NULL AUTO_INCREMENT,
    private String resourcesId;// varchar(20) DEFAULT '' COMMENT '资源Id',
    private String roleId;// varchar(20) DEFAULT '' COMMENT '角色id',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
