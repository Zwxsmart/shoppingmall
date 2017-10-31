package com.lanwantec.manage.entity;

/**
 * 权限资源
 */
public class Resources {
    private Integer id;// int(4) NOT NULL,
    private String name;// varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源名称',
    private String type;// varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接口或菜单类型 ',
    private String url;// varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '链接',
    private String permission;// varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '权限',
    private Integer parentId;// int(4) DEFAULT NULL COMMENT '资源 父ID',
    private String parentIds;// varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '层级',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }
}
