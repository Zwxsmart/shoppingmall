package com.lanwantec.manage.manage.user;

import com.lanwantec.manage.entity.User;
import com.lanwantec.manage.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Object queryByData(Integer pageNumber, Integer pageSize, String userName, String startTime, String endTime) {
        String sql = "select * from tbl_user where 1=1";
        if(userName != null && !userName.equals("")){
            sql += " AND name like concat('%','"+userName+"','%')";
        }
        if(startTime != null && !startTime.equals("")){
            sql+=" AND DATE_FORMAT(createDate,'%Y-%m-%d')>='" + startTime + "' ";
        }
        if(endTime != null && !endTime.equals("")){
            sql+=" AND DATE_FORMAT(createDate,'%Y-%m-%d')<='" + endTime + "' ";
        }
        if(pageNumber!=null && pageSize!=null) {
            sql += " order by createDate desc limit " + (pageNumber - 1) * pageSize + ", " + pageSize;
        }else{
            sql += " order by createDate desc ";
        }
        System.out.println(sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for(Map<String,Object> m: list){
           String sql1 = "select count(t.loginCount) from (select DATE_FORMAT( al.requestDate, '%Y-%m-%d') as loginCount from tbl_access_log al " +
                   "LEFT JOIN tbl_user u1 on u1.userNo = al.userNo where u1.userNo = " + m.get("userNo") +
                   " GROUP BY DATE_FORMAT( al.requestDate, '%Y-%m-%d'))t ";
            m.put("loginCount", jdbcTemplate.queryForObject(sql1, Integer.class) +"次");
        }
        return list;
    }

    public Object countByData(String userName, String startTime, String endTime) {
        String sql = "select COUNT(userNo) from tbl_user where 1=1";
        if(userName != null && !userName.equals("")){
            sql += " AND name like concat('%','"+userName+"','%')";
        }
        if(startTime != null && !startTime.equals("")){
            sql+=" AND DATE_FORMAT(createDate,'%Y-%m-%d')>='" + startTime + "' ";
        }
        if(endTime != null && !endTime.equals("")){
            sql+=" AND DATE_FORMAT(createDate,'%Y-%m-%d')<='" + endTime + "' ";
        }
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Map<String, Object> queryById(String userNo) {
        String sql = "SELECT * FROM tbl_user WHERE userNo =?";
        List<Map<String, Object>> list =jdbcTemplate.queryForList(sql, userNo);
        return list.size() > 0 ? list.get(0) : null;
    }

    public Map<String,Object> queryByBrowse(String userNo) {
        return null;
    }

    public Integer add(String userNo, String name, String nick, String gender, String phone, String birth, String address) {
        String sql = "";
        String[] sourceStrArray = address.split("/");
        if(!userNo.equals("")&&userNo!=null&&!userNo.equals("undefined")){
            if(sourceStrArray.length==1) {
                sql = "update tbl_user set name=?, nick=?, phone=?, gender=?, country=?, birth=?,province=?,city=?, wxOpenId=?, userType=?, userTypeName=? where userNo =?";
                return jdbcTemplate.update(sql, name, nick, phone, gender, sourceStrArray[0], birth,"","","", 1, "", userNo);
            }
            if(sourceStrArray.length==2) {
                sql = "update tbl_user set name=?, nick=?, phone=?, gender=?, country=?, birth=?, province=?,city=?,wxOpenId=?, userType=?, userTypeName=? where userNo =?";
                return jdbcTemplate.update(sql, name, nick, phone, gender, sourceStrArray[0], birth, sourceStrArray[1],"","",1,"", userNo);
            }
            if(sourceStrArray.length==3) {
                sql = "update tbl_user set name=?, nick=?, phone=?, gender=?, country=?, birth=?, province=?, city=?, wxOpenId=?, userType=?, userTypeName=? where userNo =?";
                return jdbcTemplate.update(sql, name, nick, phone, gender, sourceStrArray[0], birth, sourceStrArray[1], sourceStrArray[2], "", 1, "", userNo);
            }
        }else{
            if(sourceStrArray.length==1) {
                sql = "INSERT INTO tbl_user(userNo, name, nick, phone, gender, country, birth,province, city, wxOpenId, userType, userTypeName,createDate,portrait) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(),?)";
                return jdbcTemplate.update(sql, IDUtils.createUserNo(), name, nick, phone, gender, sourceStrArray[0], birth,"","", "", 1, "","/upload/images/defaultAvatar.png");
            }
            if(sourceStrArray.length==2) {
                sql = "INSERT INTO tbl_user(userNo, name, nick, phone, gender, country, birth, province, city, wxOpenId, userType, userTypeName,createDate,portrait) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(),?)";
                return jdbcTemplate.update(sql, IDUtils.createUserNo(), name, nick, phone, gender, sourceStrArray[0], birth, sourceStrArray[1],"", "", 1, "","/upload/images/defaultAvatar.png");
            }
            if(sourceStrArray.length==3) {
                sql = "INSERT INTO tbl_user(userNo, name, nick, phone, gender, country, birth, province, city, wxOpenId, userType, userTypeName,createDate,portrait) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(),?)";
                return jdbcTemplate.update(sql, IDUtils.createUserNo(), name, nick, phone, gender, sourceStrArray[0], birth, sourceStrArray[1], sourceStrArray[2], "", 1, "","/upload/images/defaultAvatar.png");
            }
        }
        return 0;
    }

    public Integer insert(String userNo, String name, String nick, String gender, String phone, String birth, String address, Date createDate) {
        String sql = "";
        String[] sourceStrArray = address.split("/");
        if(sourceStrArray.length==1) {
            sql = "INSERT INTO tbl_user(userNo, name, nick, phone, gender, country, birth,province, city, wxOpenId, userType, userTypeName,createDate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, IDUtils.createUserNo(), name, nick, phone, gender, sourceStrArray[0],birth,"","", "", 1, "", createDate);
        }
        if(sourceStrArray.length==2) {
            sql = "INSERT INTO tbl_user(userNo, name, nick, phone, gender, country, birth, province, city,wxOpenId, userType, userTypeName,createDate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, IDUtils.createUserNo(), name, nick, phone, gender, sourceStrArray[0], birth, sourceStrArray[1],"", "", 1, "", createDate);
        }
        if(sourceStrArray.length==3) {
            sql = "INSERT INTO tbl_user(userNo, name, nick, phone, gender, country, birth, province, city, wxOpenId, userType, userTypeName,createDate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, IDUtils.createUserNo(), name, nick, phone, gender, sourceStrArray[0], birth, sourceStrArray[1], sourceStrArray[2], "", 1, "", createDate);
        }
        return 0;
    }

    public Integer status(String status, String userNo) {
        String sql = "update tbl_user set isValid = ? where userNo = ?";
        if(status.equals("Y")){
            return jdbcTemplate.update(sql, 0, userNo);
        }else{
            return jdbcTemplate.update(sql, 1, userNo);
        }
    }

}
