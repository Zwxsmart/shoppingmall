package com.lanwantec.manage.entity;

/**
 * 角色管理员对应关系
 */
public class RoleOper {
    private String operNo;// varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户ID',
    private Integer roleId;// int(4) unsigned NOT NULL COMMENT '角色ID',
    private Integer groupId;// int(11) DEFAULT '0' COMMENT '关联分组表ID'

    public String getOperNo() {
        return operNo;
    }

    public void setOperNo(String operNo) {
        this.operNo = operNo;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
