package com.lanwantec.manage.utils;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SecuritySessionUtils {

    private static Logger log = Logger.getLogger(SecuritySessionUtils.class);

    public static String getBrandNo() {
        try {
            Session session = SecurityUtils.getSubject().getSession();
            String brand = session.getAttribute("brand").toString();
            return brand;
//            return "P00210118";
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

    public static Integer getUserType() {
        try {
            Session session = SecurityUtils.getSubject().getSession();
            Integer userType = Integer.parseInt(session.getAttribute("userType").toString());
            return userType;
//            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

    public static String getOperName() {
        try {
            Session session = SecurityUtils.getSubject().getSession();
            String operName = session.getAttribute("operName").toString();
            return operName;
//            return "rainy";
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

    public static int getOperBrandType() {
        try {
            Session session = SecurityUtils.getSubject().getSession();
            int type = (int) session.getAttribute("type");
//          return 1;
            return type;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return -1;
        }
    }

    public static String getOperBrandTypeNo() {
        try {
            Session session = SecurityUtils.getSubject().getSession();
            String typeNo = session.getAttribute("typeNo").toString();
            return typeNo;
//            return "J00210118";
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

    public static String getOperNo() {
        try {
            Session session = SecurityUtils.getSubject().getSession();
            String operateNo = session.getAttribute("operateNo").toString();
            return operateNo;
//            return "rainy";
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

    public static String getGroupId() {
        try {
            Session session = SecurityUtils.getSubject().getSession();
            String groupId = session.getAttribute("groupId").toString();
            return groupId;
//            return "rainy";
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return "-1";
        }
    }

    public static List<Map<String,Object>> GetStoreGroupList(){
        try {
            Session session = SecurityUtils.getSubject().getSession();
            return (List<Map<String, Object>>) session.getAttribute("storeList");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            List<Map<String,Object>> list = new ArrayList<>();
            Map<String,Object> map = new HashedMap();
            map.put("groupId",-1);
            list.add(map);
            return list;
        }
    }

}
