package com.lanwantec.manage.manage.store;

import com.lanwantec.manage.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class StoreDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Object queryByData(Integer pageNumber, Integer pageSize, String brandName, String floor, String storeStyle, String startTime, String endTime) {
        String sql = "select s.*, sm.floor*1 as floor, sm.doorplate as doorplate, " +
                "(select count(g.goodsId) from tbl_goods g " +
                "LEFT JOIN tbl_brand b1 on g.brandNo = b1.brandNo " +
                "LEFT JOIN tbl_brand_to_store bs1 on b1.brandNo = bs1.brandNo " +
                "LEFT JOIN tbl_store s1 on s1.storeId = bs1.storeId where s1.storeId = s.storeId) " +
                "as goodsTotal, b.name as brandName from tbl_store s " +
                "LEFT JOIN tbl_store_to_mall sm on sm.storeId = s.storeId " +
                "LEFT JOIN tbl_brand_to_store bs on bs.storeId = s.storeId " +
                "LEFT JOIN tbl_brand b on bs.brandNo = b.brandNo " +
                "LEFT JOIN tbl_goods g1 on g1.brandNo = b.brandNo where 1=1 ";
        if(brandName != null && !brandName.equals("")){
            sql += " and b.name like concat('%','"+brandName+"','%')";
        }
        if(floor!=null && !floor.equals("") && !floor.equals("0")){
            sql += " and sm.floor ='"+floor+"'";
        }
        if(storeStyle!=null && !storeStyle.equals("") && !storeStyle.equals("0")){
            sql +="and g1.styleNo like concat('%','"+storeStyle+"','%')";
        }
        if(startTime != null && !startTime.equals("")){
            sql+=" AND DATE_FORMAT(s.createDate,'%Y-%m-%d')>='" + startTime + "' ";
        }
        if(endTime != null && !endTime.equals("")){
            sql+=" AND DATE_FORMAT(s.createDate,'%Y-%m-%d')<='" + endTime + "' ";
        }
        if(pageNumber!=null && pageSize!=null) {
            sql+=" order by createDate desc limit "+(pageNumber-1)*pageSize+", " + pageSize;
        }else{
            sql+=" order by createDate desc  ";
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public Object countByData(String brandName, String floor, String storeStyle, String startTime, String endTime) {
        String sql = "select count(s.storeId) from tbl_store s " +
                "LEFT JOIN tbl_store_to_mall sm on sm.storeId = s.storeId " +
                "LEFT JOIN tbl_brand_to_store bs on bs.storeId = s.storeId " +
                "LEFT JOIN tbl_brand b on bs.brandNo = b.brandNo " +
                "LEFT JOIN tbl_goods g1 on g1.brandNo = b.brandNo where 1=1 ";
        if(brandName != null && !brandName.equals("")){
            sql += " and b.name like concat('%','"+brandName+"','%')";
        }
        if(floor!=null && !floor.equals("") && !floor.equals("0")){
            sql += " and sm.floor ='"+floor+"'";
        }
        if(storeStyle!=null && !storeStyle.equals("") && !storeStyle.equals("0")){
            sql +="and g1.styleNo like concat('%','"+storeStyle+"','%')";
        }
        if(startTime != null && !startTime.equals("")){
            sql+=" AND DATE_FORMAT(s.createDate,'%Y-%m-%d')>='" + startTime + "' ";
        }
        if(endTime != null && !endTime.equals("")){
            sql+=" AND DATE_FORMAT(s.createDate,'%Y-%m-%d')<='" + endTime + "' ";
        }
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Map<String, Object> queryById(String storeId) {
        String sql = "select s.*, sm.floor*1 as floor,bs.id as bsid, sm.id as smid, b.brandNo as brandNo, b.name as brandName,m.mallId as mallId, sm.doorplate as doorplate from tbl_store s " +
                "LEFT JOIN tbl_store_to_mall sm on sm.storeId = s.storeId " +
                "LEFT JOIN tbl_mall m on sm.mallId = m.mallId " +
                "LEFT JOIN tbl_brand_to_store bs on bs.storeId = s.storeId " +
                "LEFT JOIN tbl_brand b on bs.brandNo = b.brandNo where 1=1 and s.storeId = ?";
        List<Map<String, Object>> list =jdbcTemplate.queryForList(sql, storeId);
        return list.size() > 0 ? list.get(0) : null;
    }

    public Integer status(String status, String storeId) {
        String sql = "update tbl_store set isValid = ? where storeId = ?";
        if(status.equals("Y")){
            return jdbcTemplate.update(sql, 0, storeId);
        }else{
            return jdbcTemplate.update(sql, 1, storeId);
        }
    }

    public List<Map<String,Object>> queryAllMall() {
        String sql = "select mallId,name from tbl_mall where mallid != 0";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public List<Map<String,Object>> queryAllBrand() {
        String sql = "select brandNo,name from tbl_brand";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public Integer update(String storeId, String name, String principal, String contacter,String contactPhone,
                          String storePhone, String doorplate, String openingHours, String address,
                          String detailAddress, String longitude, String latitude,String provideService, String showUrl) {
        String sql = "";
        String[] sourceStrArray =  address.split("/");
        if(storeId!=null&!storeId.equals("")){
            if(showUrl!=null&showUrl!="") {
                if(sourceStrArray.length==1) {
                    sql = "update tbl_store set name = ?, dealerId = ?, country=?,province=?, city=?, address=?, principal=?, contacter=?, " +
                            "contactPhone=?, storePhone=?, longitude=?, latitude=?, showUrl=?, openingHours=?, provideService=?, updateDate=now(), showUrl=? where storeId =?";
                    return jdbcTemplate.update(sql, name, 1, sourceStrArray[0], "","",detailAddress, principal, contacter, contactPhone, storePhone, longitude, latitude, "", openingHours, provideService, showUrl, storeId);
                }
                if(sourceStrArray.length==2){
                    sql = "update tbl_store set name = ?, dealerId = ?, country=?, province=?, city=?, address=?, principal=?, contacter=?, " +
                            "contactPhone=?, storePhone=?, longitude=?, latitude=?, showUrl=?, openingHours=?, provideService=?, updateDate=now(), showUrl=? where storeId =?";
                    return jdbcTemplate.update(sql, name, 1, sourceStrArray[0], sourceStrArray[1],"", detailAddress, principal, contacter, contactPhone, storePhone, longitude, latitude, "", openingHours, provideService, showUrl, storeId);
                }
                if(sourceStrArray.length==3){
                    sql = "update tbl_store set name = ?, dealerId = ?, country=?, province=?, city=?, address=?, principal=?, contacter=?, " +
                            "contactPhone=?, storePhone=?, longitude=?, latitude=?, showUrl=?, openingHours=?, provideService=?, updateDate=now(), showUrl=? where storeId =?";
                    return jdbcTemplate.update(sql, name, 1, sourceStrArray[0], sourceStrArray[1], sourceStrArray[2], detailAddress, principal, contacter, contactPhone, storePhone, longitude, latitude, "", openingHours, provideService, showUrl, storeId);
                }
            }else{
                if(sourceStrArray.length==1) {
                    sql = "update tbl_store set name = ?, dealerId = ?, country=?,province=?, city=?, address=?, principal=?, contacter=?, " +
                            "contactPhone=?, storePhone=?, longitude=?, latitude=?, showUrl=?, openingHours=?, provideService=?, updateDate=now() where storeId =?";
                    return jdbcTemplate.update(sql, name, 1, sourceStrArray[0],"","", detailAddress, principal, contacter, contactPhone, storePhone, longitude, latitude, "", openingHours, provideService, storeId);
                }
                if(sourceStrArray.length==2) {
                    sql = "update tbl_store set name = ?, dealerId = ?, country=?, province=?, city=?, address=?, principal=?, contacter=?, " +
                            "contactPhone=?, storePhone=?, longitude=?, latitude=?, showUrl=?, openingHours=?, provideService=?, updateDate=now() where storeId =?";
                    return jdbcTemplate.update(sql, name, 1, sourceStrArray[0], sourceStrArray[1], "", detailAddress, principal, contacter, contactPhone, storePhone, longitude, latitude, "", openingHours, provideService, storeId);
                }
                if(sourceStrArray.length==3) {
                    sql = "update tbl_store set name = ?, dealerId = ?, country=?, province=?, city=?, address=?, principal=?, contacter=?, " +
                            "contactPhone=?, storePhone=?, longitude=?, latitude=?, showUrl=?, openingHours=?, provideService=?, updateDate=now() where storeId =?";
                    return jdbcTemplate.update(sql, name, 1, sourceStrArray[0], sourceStrArray[1], sourceStrArray[2], detailAddress, principal, contacter, contactPhone, storePhone, longitude, latitude, "", openingHours, provideService, storeId);
                }
            }
        }else{
            if(sourceStrArray.length==1) {
                sql = "INSERT INTO tbl_store(name, dealerId, country,province, city,address, principal, contacter, contactPhone, storePhone, longitude, latitude, showUrl, " +
                        "openingHours, provideService, createDate,updateDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , now(), now()) ";
                return jdbcTemplate.update(sql, name, 1, sourceStrArray[0],"","", detailAddress, principal, contacter, contactPhone, storePhone, longitude, latitude, showUrl, openingHours, provideService);
            }
            if(sourceStrArray.length==2) {
                sql = "INSERT INTO tbl_store(name, dealerId, country, province, city, address, principal, contacter, contactPhone, storePhone, longitude, latitude, showUrl, " +
                        "openingHours, provideService, createDate,updateDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , now(), now()) ";
                return jdbcTemplate.update(sql, name, 1, sourceStrArray[0], sourceStrArray[1],"", detailAddress, principal, contacter, contactPhone, storePhone, longitude, latitude, showUrl, openingHours, provideService);
            }
            if(sourceStrArray.length==3) {
                sql = "INSERT INTO tbl_store(name, dealerId, country, province, city, address, principal, contacter, contactPhone, storePhone, longitude, latitude, showUrl, " +
                        "openingHours, provideService, createDate,updateDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , now(), now()) ";
                return jdbcTemplate.update(sql, name, 1, sourceStrArray[0], sourceStrArray[1], sourceStrArray[2], detailAddress, principal, contacter, contactPhone, storePhone, longitude, latitude, showUrl, openingHours, provideService);
            }
        }
        return 0;
    }

    public Integer updateBrand(String bsid,String brand, String storeId) {
        String sql="";
        if(bsid!=null&!bsid.equals("")){
            sql="update tbl_brand_to_store set brandNo=?, storeId=?, updateDate=now() where id=?";
            return jdbcTemplate.update(sql,brand, storeId, bsid);
        }else{
            sql="INSERT INTO tbl_brand_to_store(brandNo, storeId, updateDate) VALUES (?,(select max(storeId) from tbl_store),now())";
            return jdbcTemplate.update(sql,brand);
        }
    }

    public Integer updateMall(String smid, String mall, String storeId, String floor, String doorplate) {
        String sql="";
        if(smid!=null&!smid.equals("")){
            sql="update tbl_store_to_mall set mallId=?, storeId=?, floor=?, doorplate=?, updateDate=now() where id=?";
            return jdbcTemplate.update(sql,mall,storeId,floor,doorplate, smid);
        }else{
            sql="INSERT INTO tbl_store_to_mall(mallId, storeId, floor,doorplate, updateDate) VALUES (?,(select last_insert_id() from tbl_store LIMIT 0,1),?,?,now())";
            return jdbcTemplate.update(sql,mall, floor,doorplate);
        }
    }

    public List<Map<String,Object>> queryStyles() {
        String sql = "select goodsStyleNo,styleName from tbl_goods_style";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public Integer insert(String name, String principal, String contacter, String contactPhone, String storePhone,String provideService, String address, String detailAddress, String openingHours) {
        String[] sourceStrArray = address.split("/");
        if (sourceStrArray.length == 1) {
            String sql = "INSERT INTO tbl_store(name,dealerId, principal, contacter, contactPhone, storePhone,provideService, country, province, city, address, openingHours, createDate, updateDate) " +
                    " VALUES (?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, now(), now()) ";
            return jdbcTemplate.update(sql, name, 1, principal, contacter, contactPhone, storePhone, provideService, sourceStrArray[0],"","", detailAddress, openingHours);
        }
        if(sourceStrArray.length==2) {
            String sql = "INSERT INTO tbl_store(name,dealerId, principal, contacter, contactPhone, storePhone,provideService, country, province, city, address, openingHours, createDate, updateDate) " +
                    " VALUES (?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, now(), now()) ";
            return jdbcTemplate.update(sql, name, 1, principal, contacter, contactPhone, storePhone, provideService, sourceStrArray[0], sourceStrArray[1], "", detailAddress, openingHours);
        }
        if(sourceStrArray.length==3) {
            String sql = "INSERT INTO tbl_store(name,dealerId, principal, contacter, contactPhone, storePhone,provideService, country, province, city, address, openingHours, createDate, updateDate) " +
                    " VALUES (?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, now(), now()) ";
            return jdbcTemplate.update(sql, name, 1, principal, contacter, contactPhone, storePhone, provideService, sourceStrArray[0], sourceStrArray[1], sourceStrArray[2], detailAddress, openingHours);
        }
        return 0;
    }
}
