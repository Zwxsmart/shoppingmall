package com.lanwantec.manage.manage.webSetting;

import com.lanwantec.manage.entity.Info;
import com.lanwantec.manage.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WebSettingDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, List<Map<String, Object>>> queryByIndexSetting() {
        String sql1 = "select * FROM tbl_headerInfo where moduleNo =1 and isValid =1";
        List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql1);
        String sql2 = "select * FROM tbl_headerInfo where moduleNo =2";
        List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sql2);
        String sql3 = "select * FROM tbl_headerInfo where moduleNo =3";
        List<Map<String, Object>> list3 = jdbcTemplate.queryForList(sql3);
        Map<String, List<Map<String, Object>>> headerInfo = new HashMap<>();
        headerInfo.put("carousel",list1);
        headerInfo.put("prefecture",list2);
        headerInfo.put("qrCode",list3);
        return headerInfo;
    }

    public List<Map<String, Object>> queryByIndexSetting1() {
        String sql1 = "select * FROM tbl_headerInfo where moduleNo =5";
        List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql1);
        return list1;
    }

    public Integer updateGroupIntroduction(Info info) {
        String sql = "update tbl_info set title = ?,content = ? where id = ?";
        return jdbcTemplate.update(sql,info.getTitle(), info.getContent(), info.getId());
    }

    public Integer updateGroupIntroduction(Info info, String filePath) {
        String sql = "update tbl_info set title = ?,content = ?, picTitle=? where id = ?";
        return jdbcTemplate.update(sql,info.getTitle(), info.getContent(), filePath, info.getId());
    }

    public List<Map<String,Object>> queryByInfoList(int pageNumber, int pageSize, String infoType) {
        String sql = "select * FROM tbl_info WHERE infoType = ? ORDER BY sortIndex desc limit "+(pageNumber-1)*pageSize+", " + pageSize;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, infoType);
        return list;
    }

    public Object queryByInfoCount(String infoType) {
        String sql = "select count(id) FROM tbl_info WHERE infoType = "+infoType;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Integer update(String id, String title, String picTitle, String content,int isValid, String operNo, String operName) {
        String sql = "";
        if(id==null | "".equals(id)){
            sql = "INSERT INTO tbl_info(picTitle, title, content,isValid, operNo, operName, infoType, infoTypeName, createDate) " +
                    "VALUES (?, ?, ?, ?,?,?,'2', '集团动态',now())";
            return jdbcTemplate.update(sql, picTitle, title, content,isValid, operNo, operName);
        }else{
            if(picTitle==null|"".equals(picTitle)){
                return 0;
            }else {
                sql = "update tbl_info set picTitle=?, title=?, content=?, isValid=?, operNo=?, operName=?  where id =?";
                return jdbcTemplate.update(sql, picTitle, title, content, isValid, operNo, operName, id);
            }
        }
    }
    public Integer update1(String id, String title, String picTitle, String operNo, String operName) {
        String sql = "";
        if(id==null | "".equals(id)){
            sql = "INSERT INTO tbl_info(title, picTitle,operNo, operName, infoType, infoTypeName, createDate) " +
                    "VALUES (?,?,?,?,'4', '企业荣誉',now())";
            return jdbcTemplate.update(sql,title, picTitle, operNo, operName);
        }else{
            if(picTitle==null|"".equals(picTitle)){
                return 0;
            }else {
                sql = "update tbl_info set picTitle=?, operNo=?, operName=?, title=?  where id =?";
                return jdbcTemplate.update(sql, picTitle,  operNo, operName,title, id);
            }
        }
    }

    public Integer update2(String id, String title, String picTitle, String content,int isValid, String operNo, String operName) {
        String sql = "";
        if(id==null | "".equals(id)){
            sql = "INSERT INTO tbl_info(picTitle, title, content,isValid, operNo, operName, infoType, infoTypeName, createDate) " +
                    "VALUES (?, ?, ?, ?,?,?,'12', '居然之家热门活动',now())";
            return jdbcTemplate.update(sql, picTitle, title, content,isValid, operNo, operName);
        }else{
            if(picTitle==null|"".equals(picTitle)){
                return 0;
            }else {
                sql = "update tbl_info set picTitle=?, title=?, content=?, isValid=?, operNo=?, operName=?  where id =?";
                return jdbcTemplate.update(sql, picTitle, title, content, isValid, operNo, operName, id);
            }
        }
    }

    public Integer update3(String id, String title, String picTitle, String content,int isValid, String operNo, String operName) {
        String sql = "";
        if(id==null | "".equals(id)){
            sql = "INSERT INTO tbl_info(picTitle, title, content,isValid, operNo, operName, infoType, infoTypeName, createDate) " +
                    "VALUES (?, ?, ?, ?,?,?,'14', '联盟国际热门活动',now())";
            return jdbcTemplate.update(sql, picTitle, title, content,isValid, operNo, operName);
        }else{
            if(picTitle==null|"".equals(picTitle)){
                return 0;
            }else {
                sql = "update tbl_info set picTitle=?, title=?, content=?, isValid=?, operNo=?, operName=?  where id =?";
                return jdbcTemplate.update(sql, picTitle, title, content, isValid, operNo, operName, id);
            }
        }
    }

    public Integer update4(String id, String title, String picTitle, String content,int isValid, String operNo, String operName,String tagName, String startTime, String endTime) {
        String sql = "";
        if(id==null | "".equals(id)){
            sql = "INSERT INTO tbl_campaign(picTitle, title, content,isValid, operNo, operName, tagName, beginDate,finishDate, createDate) " +
                    "VALUES (?, ?, ?, ?,?,?,?,?,?,now())";
            return jdbcTemplate.update(sql, picTitle, title, content,isValid, operNo, operName, tagName, startTime, endTime);
        }else{
            if(picTitle==null|"".equals(picTitle)){
                return 0;
            }else {
                sql = "update tbl_campaign set picTitle=?, title=?, content=?, isValid=?, operNo=?, operName=?, tagName=?,beginDate=?, finishDate=? where id =?";
                return jdbcTemplate.update(sql, picTitle, title, content, isValid, operNo, operName,tagName,startTime,endTime, id);
            }
        }
    }

    public Map<String,Object> queryById(String id) {
        String sql = "SELECT * FROM tbl_info WHERE id =?";
        List<Map<String, Object>> list =jdbcTemplate.queryForList(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public Map<String,Object> queryTextareaInfo(int infoType) {
        String sql = "SELECT * FROM tbl_info WHERE infoType = "+infoType;
        List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
        return list.size() > 0 ? list.get(0) : null;
    }

    public Integer updateContent(Info info) {
        String sql = "update tbl_info set content=?,operNo=?, operName=? where id =?";
        return jdbcTemplate.update(sql, info.getContent(), info.getOperNo(), info.getOperName(), info.getId());
    }

    public Integer updateInfo(String id, String title, String picTitle, String content, String operNo, String operName) {
        String sql = "update tbl_info set picTitle=?, title=?, content=?,operNo=?, operName=?  where id =?";
        return jdbcTemplate.update(sql, picTitle, title, content, operNo, operName, id);
    }

    public Integer updateCarousel(String id, String gotoUrl, String picUrl) {
        if(picUrl==null | "".equals(picUrl)){
            String sql = "update tbl_headerInfo set gotoUrl=? where id =?";
            return jdbcTemplate.update(sql, gotoUrl, id);
        }else{
            String sql = "update tbl_headerInfo set gotoUrl=?,picUrl=? where id =?";
            return jdbcTemplate.update(sql, gotoUrl, picUrl, id);
        }
    }

    public Integer updateCarousel1(String id, String gotoUrl, String picUrl, String title) {
        if(picUrl==null | "".equals(picUrl)){
            String sql = "update tbl_headerInfo set gotoUrl=?, title=? where id =?";
            return jdbcTemplate.update(sql, gotoUrl,title, id);
        }else{
            String sql = "update tbl_headerInfo set gotoUrl=?,picUrl=?, title=? where id =?";
            return jdbcTemplate.update(sql, gotoUrl, picUrl,title, id);
        }
    }

    public Integer insertCarousel(String gotoUrl, String picUrl, String type, String typeName, String title) {
        if(picUrl==null | "".equals(picUrl)){
            return 0;
        }else{
            String sql = "insert into tbl_headerInfo (moduleNo, moduleName, title, picUrl, gotoUrl, createDate) values (?, ?, ?, ?, ?, now())";
            return jdbcTemplate.update(sql, type, typeName,title,picUrl, gotoUrl);
        }
    }

    public Integer updatePrefecture(String id,String title, String picUrl) {
        if(picUrl==null | "".equals(picUrl)){
            String sql = "update tbl_headerInfo set title=? where id =?";
            return jdbcTemplate.update(sql, title,id);
        }else{
            String sql = "update tbl_headerInfo set title=?, picUrl=? where id =?";
            return jdbcTemplate.update(sql, title, picUrl, id);
        }
    }

    public Integer updateQrCode(String id, String picUrl) {
        String sql = "update tbl_headerInfo set picUrl=? where id =?";
        return jdbcTemplate.update(sql, picUrl, id);
    }

    public Integer headerInfoStatus(String id) {
        String sql = "update tbl_headerInfo set isValid=0 where id =?";
        return jdbcTemplate.update(sql,  id);
    }

    public Integer stick(Integer id) {
        String sql = "update tbl_info set sortIndex=(select sortIndex from (select max(sortIndex)+1 as sortIndex from tbl_info) aa)  where id =?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Map<String,Object>> queryByHeaderInfo(int type) {
        String sql1 = "select * FROM tbl_headerInfo where moduleNo ="+type+" and isValid=1";
        List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql1);
        return list1;
    }

    public Integer release(String id, String isValid) {
        String sql = "update tbl_info set isValid = ? where id = ?";
        if(isValid.equals("Y")){
            return jdbcTemplate.update(sql, 0, id);
        }else{
            return jdbcTemplate.update(sql, 1, id);
        }
    }

    public Integer release1(String id, String isValid) {
        String sql = "update tbl_campaign set isValid = ? where id = ?";
        if(isValid.equals("Y")){
            return jdbcTemplate.update(sql, 0, id);
        }else{
            return jdbcTemplate.update(sql, 1, id);
        }
    }

    public Integer release2(String id, String isValid) {
        String sql = "update tbl_release_point set isValid = ? where id = ?";
        if(isValid.equals("Y")){
            return jdbcTemplate.update(sql, 0, id);
        }else{
            return jdbcTemplate.update(sql, 1, id);
        }
    }

    public Integer release3(String id, String isValid) {
        String sql = "update tbl_campaign set sortIndex = ? where id = ?";
        if(isValid.equals("Y")){
            return jdbcTemplate.update(sql, 0, id);
        }else{
            return jdbcTemplate.update(sql, 1, id);
        }
    }

    public List<Map<String,Object>> queryByCampaign(int pageNumber, int pageSize) {
        String sql = "select * FROM tbl_campaign order by createDate desc limit "+(pageNumber-1)*pageSize+", " + pageSize;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public List<Map<String,Object>> queryByCampaign() {
        String sql = "select * FROM tbl_campaign ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public Integer queryByByCampaignCount() {
        String sql = "select count(id) FROM tbl_campaign";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Map<String,Object> queryCampaignById(String id) {
        String sql = "SELECT * FROM tbl_campaign WHERE id =?";
        List<Map<String, Object>> list =jdbcTemplate.queryForList(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Map<String,Object>> queryByPointList(int pageNumber, int pageSize, String id) {
        String sql = " select p.*, (select count(id) from tbl_campaign_browse where releasePointId=p.id and campaignId = ?) as status " +
                " from tbl_release_point p where p.isValid=1 order by p.createDate desc limit "+(pageNumber-1)*pageSize+", " + pageSize;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, id);
        return list;
    }

    public Integer queryByPointCount() {
        String sql =  " select count(id) FROM tbl_release_point where isValid=1 ";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public List<Map<String,Object>> queryPointList(int pageNumber, int pageSize) {
        String sql = " select * from tbl_release_point order by createDate desc limit "+(pageNumber-1)*pageSize+", " + pageSize;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public Integer queryPointCount() {
        String sql =  " select count(id) from tbl_release_point ";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Integer update5(String id, String title) {
        String sql = "";
        if(id==null | "".equals(id)){
            sql = "INSERT INTO tbl_release_point(pointName, createDate) " +
                    "VALUES (?, now())";
            return jdbcTemplate.update(sql, title);
        }else{
                sql = "update tbl_release_point set pointName=? where id =?";
                return jdbcTemplate.update(sql, title, id);
        }
    }

    public Map<String,Object> queryPointId(String id) {
        String sql = "SELECT * FROM tbl_release_point WHERE id =?";
        List<Map<String, Object>> list =jdbcTemplate.queryForList(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public Integer checkComit(String name, String phone, String address, String releasePointId, String campaignId) {
        String sql = "select userNo from tbl_user where phone = "+phone;
        List<Map<String, Object>> list =jdbcTemplate.queryForList(sql);
        String userNo=null;
        if(list==null | list.size()==0){// 查询数据库是否有账号, 没有则直接新增一个用户
            userNo = IDUtils.createUserNo();
            if(address==null | ("").equals(address)){
                sql = "INSERT INTO tbl_user(userNo, name, phone, wxOpenId, userType, userTypeName,createDate,portrait) " +
                        "VALUES (?, ?, ?, '', 1, '', now(),'/upload/images/defaultAvatar.png')";
                    jdbcTemplate.update(sql, userNo, name,  phone);
            }else{
                String[] sourceStrArray = address.split("/");
                if(sourceStrArray.length==1) {
                    sql = "INSERT INTO tbl_user(userNo, name, phone,country, province, city, wxOpenId, userType, userTypeName,createDate,portrait) " +
                            "VALUES (?, ?, ?, ?, '', '', '', 1, '', now(), '/upload/images/defaultAvatar.png')";
                    jdbcTemplate.update(sql, userNo, name, phone, sourceStrArray[0]);
                }
                if(sourceStrArray.length==2) {
                    sql = "INSERT INTO tbl_user(userNo, name, phone,country, province, city, wxOpenId, userType, userTypeName,createDate,portrait) " +
                            "VALUES (?, ?, ?, ?, ?, '', '', 1, '', now(),'/upload/images/defaultAvatar.png')";
                    jdbcTemplate.update(sql, userNo, name, phone, sourceStrArray[0],sourceStrArray[1]);
                }
                if(sourceStrArray.length==3) {
                    sql = "INSERT INTO tbl_user(userNo, name, phone,country, province, city, wxOpenId, userType, userTypeName,createDate,portrait) " +
                            "VALUES (?, ?, ?, ?, ?, ?, '', 1, '', now(), '/upload/images/defaultAvatar.png')";
                    jdbcTemplate.update(sql, userNo, name, phone, sourceStrArray[0],sourceStrArray[1],sourceStrArray[2]);
                }
            }
        }else{ // 否则则更新最新信息
            userNo = (String)list.get(0).get("userNo");
            if(address==null | ("").equals(address)){
                sql = "update tbl_user set name=? where phone =?";
                jdbcTemplate.update(sql, name,  phone);
            }else {
                String[] sourceStrArray = address.split("/");
                if (sourceStrArray.length == 1) {
                    sql = "update tbl_user set name=?,country=?, province='',city='' where phone =?";
                    jdbcTemplate.update(sql, name, sourceStrArray[0], phone);
                }
                if (sourceStrArray.length == 2) {
                    sql = "update tbl_user set name=?,country=?, province=?,city='' where phone =?";
                    jdbcTemplate.update(sql, name, sourceStrArray[0],sourceStrArray[1], phone);
                }
                if (sourceStrArray.length == 3) {
                    sql = "update tbl_user set name=?,country=?, province=?,city=? where phone =?";
                    jdbcTemplate.update(sql, name, sourceStrArray[0],sourceStrArray[1],sourceStrArray[2], phone);
                }
            }
        }
        sql = "INSERT INTO tbl_campaign_checkin(userNo, releasePointId, campaignId,comment,createDate) " +
                "VALUES (?,?,?,?, now())";
        return jdbcTemplate.update(sql, userNo, releasePointId, campaignId, "");
    }

    public List<Map<String,Object>> queryActivityCountList(Integer pageNumber, Integer pageSize, String activityName, String startTime, String endTime) {
        String sql = " select c.*,(select count(id) FROM tbl_campaign_checkin where campaignId = c.id)as checkCount,  " +
                " (select count(id) from tbl_campaign_browse where campaignId = c.id) as putCount, " +
                " (select sum(browseQuantity) from tbl_campaign_browse where campaignId = c.id) as browseQuantity  " +
                " from tbl_campaign c where 1=1 ";
        if(activityName != null && !activityName.equals("")){
            sql += " AND c.title like concat('%','"+activityName+"','%')";
        }
        if(startTime != null && !startTime.equals("")){
            sql+=" AND DATE_FORMAT(c.beginDate,'%Y-%m-%d')>='" + startTime + "' ";
        }
        if(endTime != null && !endTime.equals("")){
            sql+=" AND DATE_FORMAT(c.beginDate,'%Y-%m-%d')<='" + endTime + "' ";
        }
        if(pageNumber!=null && pageSize!=null) {
            sql += " order by createDate desc limit " + (pageNumber - 1) * pageSize + ", " + pageSize;
        }else{
            sql += " order by createDate desc ";
        }
        System.out.println(sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public Integer queryActivityCountCount(String activityName, String startTime, String endTime) {
        String sql = " select count(c.id) from tbl_campaign c where 1=1";
        if(activityName != null && !activityName.equals("")){
            sql += " AND title like concat('%','"+activityName+"','%')";
        }
        if(startTime != null && !startTime.equals("")){
            sql+=" AND DATE_FORMAT(beginDate,'%Y-%m-%d')>='" + startTime + "' ";
        }
        if(endTime != null && !endTime.equals("")){
            sql+=" AND DATE_FORMAT(beginDate,'%Y-%m-%d')<='" + endTime + "' ";
        }
        System.out.println(sql);
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public List<Map<String,Object>> queryPointByCamId(String id) {
        String sql = " select cb.*, p.pointName as pointName,c.title as title, (select count(id) from tbl_campaign_checkin where campaignId =cb.campaignId and releasePointId = cb.releasePointId) as checkCount from tbl_campaign_browse cb " +
                " LEFT JOIN tbl_release_point p on cb.releasePointId= p.id LEFT JOIN tbl_campaign c on c.id= cb.campaignId where cb.campaignId=? ORDER BY releasePointId asc ";
        List<Map<String, Object>> list =jdbcTemplate.queryForList(sql,id);
        return list;
    }

    public Integer queryPointCountByCamId(String id) {
        String sql =  " select count(id) from tbl_campaign_browse where campaignId=?; ";
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }

    public Integer putActivity(String pointId, String camId) {
        String sql = "insert into tbl_campaign_browse (releasePointId, campaignId, createDate)VALUES(?,?, now())";
        return jdbcTemplate.update(sql, pointId, camId);
    }

    public List<Map<String,Object>> queryByOnclick(String releasePointId, String campaignId) {
        String sql = " select u.name,u.phone, u.country,u.province,u.city, rp.pointName  from  tbl_campaign_checkin  cc " +
                " LEFT JOIN tbl_user u on u.userNo = cc.userNo " +
                " LEFT JOIN tbl_release_point rp on rp.id= releasePointId " +
                " where cc.releasePointId =? and cc.campaignId=?";
        List<Map<String, Object>> list =jdbcTemplate.queryForList(sql, releasePointId,campaignId);
        return list;
    }

    public Object queryByOnclickCount(String id) {
        String sql =  " select count(id) from tbl_campaign_checkin where releasePointId =?; ";
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }

    public Integer queryByCamAndPoint(String campaignId, String releasePointId) {
        String sql =  " select count(id) from tbl_campaign_browse where campaignId=? and releasePointId =?; ";
        return jdbcTemplate.queryForObject(sql, Integer.class, campaignId, releasePointId);
    }

    public Integer insertByCamAndPoint(String campaignId, String releasePointId) {
        String sql = "insert into tbl_campaign_browse (releasePointId, campaignId, createDate,browseQuantity)VALUES(?,?, now(),'1')";
        return jdbcTemplate.update(sql, releasePointId, campaignId);
    }

    public Integer updateByCamAndPoint(String campaignId, String releasePointId) {
        String sql = "update tbl_campaign_browse set browseQuantity=browseQuantity+1 where releasePointId=? and campaignId=?";
        return jdbcTemplate.update(sql, releasePointId, campaignId);
    }
}
