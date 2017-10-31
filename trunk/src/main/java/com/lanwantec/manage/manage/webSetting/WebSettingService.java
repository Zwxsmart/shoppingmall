package com.lanwantec.manage.manage.webSetting;

import com.lanwantec.manage.entity.Info;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WebSettingService {
    @Autowired
    private WebSettingDao webSettingDao;

    public Map<String, List<Map<String, Object>>> queryByIndexSetting() {
        return webSettingDao.queryByIndexSetting();
    }

    public List<Map<String, Object>> queryByIndexSetting1() {
        return webSettingDao.queryByIndexSetting1();
    }


    public Integer updateGroupIntroduction(Info info) {
        return webSettingDao.updateGroupIntroduction(info);
    }

    public Integer updateGroupIntroduction(Info info, String filePath) {
        return webSettingDao.updateGroupIntroduction(info,filePath);
    }

    public List<Map<String, Object>> queryByInfoList(int pageNumber, int pageSize, String infoType) {
        return webSettingDao.queryByInfoList(pageNumber, pageSize,infoType);
    }

    public Integer queryByInfoCount(String infoType) {
        return (Integer) webSettingDao.queryByInfoCount(infoType);
    }

    public Map<String, Object> queryById(String id) {
        return webSettingDao.queryById(id);
    }

    public Map<String, Object> queryTextareaInfo(int infoType) {
        return webSettingDao.queryTextareaInfo(infoType);
    }

    public Integer updateContent(Info info) {
        return  webSettingDao.updateContent(info);
    }

    public Integer updateInfo(String id, String title, String picTitle, String content, String operNo, String operName) {
        return webSettingDao.updateInfo(id, title, picTitle, content, operNo, operName);
    }

    public Integer update(String id, String title, String picTitle, String content,int isVaild, String operNo, String operName) {
        return webSettingDao.update(id, title, picTitle, content, isVaild, operNo, operName);
    }
    public Integer update1(String id,String title, String picTitle, String operNo, String operName) {
        return webSettingDao.update1(id,title, picTitle,operNo, operName);
    }
    public Integer update2(String id, String title, String picTitle, String content,int isVaild, String operNo, String operName) {
        return webSettingDao.update2(id, title, picTitle, content, isVaild, operNo, operName);
    }
    public Integer update3(String id, String title, String picTitle, String content,int isVaild, String operNo, String operName) {
        return webSettingDao.update3(id, title, picTitle, content, isVaild, operNo, operName);
    }
    public Integer update4(String id, String title, String picTitle, String content,int isVaild,String tagName, String startTime, String endTime, String operNo, String operName) {
        return webSettingDao.update4(id, title, picTitle, content, isVaild, operNo, operName, tagName, startTime, endTime);
    }

    public Integer updateCarousel(String id, String gotoUrl, String picUrl) {
        return webSettingDao.updateCarousel(id, gotoUrl, picUrl);
    }
    public Integer updateCarousel1(String id, String gotoUrl, String picUrl, String title) {
        return webSettingDao.updateCarousel1(id, gotoUrl, picUrl, title);
    }


    public Integer insertCarousel(String gotoUrl, String picUrl, String type, String typeName, String title) {
        return webSettingDao.insertCarousel(gotoUrl, picUrl,type ,typeName, title);
    }

    public Integer updatePrefecture(String id, String title, String picUrl) {
        return webSettingDao.updatePrefecture(id,title, picUrl);
    }

    public Integer updateQrCode(String id, String picUrl) {
        return webSettingDao.updateQrCode(id,picUrl);
    }

    public Integer headerInfoStatus(String id) {
        return  webSettingDao.headerInfoStatus(id);
    }

    public Integer stick(Integer id) {
        return webSettingDao.stick(id);
    }

    public List<Map<String,Object>> queryByHeaderInfo(int type) {
        return webSettingDao.queryByHeaderInfo(type);
    }

    public Integer release(String id, String status) {
        return webSettingDao.release(id,status);
    }

    public Integer release1(String id, String status) {
        return webSettingDao.release1(id,status);
    }

    public Integer release2(String id, String status) {
        return webSettingDao.release2(id,status);
    }

    public Integer release3(String id, String status) {
        return webSettingDao.release3(id,status);
    }

    public List<Map<String,Object>> queryByCampaign(int pageNumber, int pageSize) {
        return webSettingDao.queryByCampaign(pageNumber, pageSize);
    }

    public List<Map<String,Object>> queryByCampaign() {
        return webSettingDao.queryByCampaign();
    }

    public Integer queryByByCampaignCount() {
        return  webSettingDao.queryByByCampaignCount();
    }

    public Map<String,Object> queryCampaignById(String id) {
        return webSettingDao.queryCampaignById(id);
    }

    public List<Map<String,Object>> queryByPointList(int pageNumber, int pageSize,String id) {
        return webSettingDao.queryByPointList(pageNumber, pageSize,id);
    }
    public Integer queryByPointCount() {
        return webSettingDao.queryByPointCount();
    }

    public List<Map<String,Object>> queryPointList(int pageNumber, int pageSize) {
        return webSettingDao.queryPointList(pageNumber,pageSize);
    }

    public Integer queryPointCount() {
        return  webSettingDao.queryPointCount();
    }


    public Integer update5(String id, String title) {
        return webSettingDao.update5(id,title);
    }

    public Map<String,Object> queryPointId(String id) {
        return webSettingDao.queryPointId(id);
    }

    public Integer checkComit(String name, String phone, String address, String releasePointId, String campaignId) {
        return webSettingDao.checkComit(name, phone, address, releasePointId,campaignId);
    }

    public List<Map<String,Object>> queryActivityCountList(Integer pageNumber, Integer pageSize, String activityName, String startTime, String endTime) {
        return webSettingDao.queryActivityCountList(pageNumber, pageSize, activityName, startTime, endTime);
    }

    public Integer queryActivityCountCount(String activityName, String startTime, String endTime) {
        return webSettingDao.queryActivityCountCount(activityName, startTime, endTime);
    }

    public List<Map<String,Object>> queryPointByCamId(String id) {
        return webSettingDao.queryPointByCamId(id);
    }

    public Integer queryPointCountByCamId(String id) {
        return webSettingDao.queryPointCountByCamId(id);
    }

    public Integer putActivity(String pointId, String camId) {
        return webSettingDao.putActivity(pointId,camId);
    }

    public List<Map<String,Object>> queryByOnclick(String releasePointId, String campaignId) {
        return webSettingDao.queryByOnclick(releasePointId,campaignId);
    }

    public Object queryByOnclickCount(String id) {
        return webSettingDao.queryByOnclickCount(id);
    }

    public Integer queryByCamAndPoint(String campaignId, String releasePointId) {
        return  webSettingDao.queryByCamAndPoint(campaignId,releasePointId);
    }

    public Integer insertByCamAndPoint(String campaignId, String releasePointId) {
        return  webSettingDao.insertByCamAndPoint(campaignId,releasePointId);
    }

    public Integer updateByCamAndPoint(String campaignId, String releasePointId) {
        return  webSettingDao.updateByCamAndPoint(campaignId,releasePointId);
    }
}
