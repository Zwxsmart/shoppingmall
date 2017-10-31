package com.lanwantec.manage.manage.webSetting;

import com.lanwantec.manage.entity.*;
import com.lanwantec.manage.utils.*;
import com.lanwantec.manage.web.login.UserLoginController;
import io.swagger.models.auth.In;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网站设置
 */
@Controller
@RequestMapping("/manage/webSetting")
public class WebSettingController {
    @Value("${brand.imgPath}")
    String brand_imgPath="";

    @Value("${server.error.path:/error}")
    private static final String ERROR_PATH ="/error/error";

    @Autowired
    private WebSettingService webSettingService;

    @Autowired
    private ReturnMessage message;

    /** 网站设置 */
    @GetMapping("/webSettingPage")
    public ModelAndView webSettingPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/web_setting");
        return mv;
    }

    /** 首页设置 */
    @GetMapping("/indexSettingPage")
    public ModelAndView indexSettingPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/header_info1");
        Map<String, List<Map<String, Object>>> m = webSettingService.queryByIndexSetting();
        if(m==null){
        }else{
            for(Map<String, Object> m1 : m.get("qrCode")){
                m1.put("showPicUrl", brand_imgPath+m1.get("picUrl"));
            }
            for(Map<String, Object> m2 : m.get("prefecture")){
                m2.put("showPicUrl", brand_imgPath+m2.get("picUrl"));
            }
            for(Map<String, Object> m3 : m.get("carousel")){
                m3.put("showPicUrl", brand_imgPath+m3.get("picUrl"));
            }
        }
        mv.addObject("headerInfo", m);
        return mv;
    }

    /** 居然之家 */
    @GetMapping("/actuallyPage")
    public ModelAndView actuallyPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/header_info2");
        List<Map<String, Object>> m = webSettingService.queryByHeaderInfo(4);
        if(m==null){
        }else{
            for(Map<String, Object> m1 : m){
                m1.put("showPicUrl", brand_imgPath+m1.get("picUrl"));
            }
        }
        mv.addObject("headerInfo", m);
        return mv;
    }

    /** 家博城pc */
    @GetMapping("/jiaBoPCPage")
    public ModelAndView jiaBoPCPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/header_info3");
        List<Map<String, Object>> m = webSettingService.queryByHeaderInfo(6);
        if(m==null){
        }else{
            for(Map<String, Object> m1 : m){
                m1.put("showPicUrl", brand_imgPath+m1.get("picUrl"));
            }
        }
        mv.addObject("headerInfo", m);
        return mv;
    }

    /** 家博城mb */
    @GetMapping("/jiaBoMBPage")
    public ModelAndView jiaBoMBPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/header_info4");
        List<Map<String, Object>> m = webSettingService.queryByHeaderInfo(7);
        if(m==null){
        }else{
            for(Map<String, Object> m1 : m){
                m1.put("showPicUrl", brand_imgPath+m1.get("picUrl"));
            }
        }
        mv.addObject("headerInfo", m);
        return mv;
    }

    /** 家具工厂设置 */
    @GetMapping("/furnitureFactory")
    public ModelAndView furnitureFactory() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/header_info5");
        List<Map<String, Object>> m = webSettingService.queryByHeaderInfo(5);
        if(m==null){
        }else{
            for(Map<String, Object> m1 : m){
                m1.put("showPicUrl", brand_imgPath+m1.get("picUrl"));
            }
        }
        mv.addObject("headerInfo", m);
        return mv;
    }

    /** 修改轮播图 */
    @ResponseBody
    @PostMapping("/updateCarousel")
    public ModelMap updateCarousel(@RequestParam(value = "id", required = true)String id,
                               @RequestParam(value = "gotoUrl", required = true)String gotoUrl,
                               String picUrl) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.updateCarousel(id,gotoUrl,picUrl);
        md.put("count", count);
        return md;
    }

    /** 修改轮播图1 */
    @ResponseBody
    @PostMapping("/updateCarousel1")
    public ModelMap updateCarousel1(@RequestParam(value = "id", required = true)String id,
                                   @RequestParam(value = "gotoUrl", required = true)String gotoUrl,
                                    @RequestParam(value = "title", required = true)String title,
                                   String picUrl) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.updateCarousel1(id,gotoUrl,picUrl, title);
        md.put("count", count);
        return md;
    }

    /** 新增轮播图 */
    @ResponseBody
    @PostMapping("/insertCarousel")
    public ModelMap insertCarousel(@RequestParam(value = "gotoUrl", required = true)String gotoUrl,
                                   @RequestParam(value = "picUrl", required = true)String picUrl,
                                   @RequestParam(value = "type", required = true)String type,
                                   @RequestParam(value = "typeName", required = true)String typeName,
                                   @RequestParam(value = "title", required = true)String title) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.insertCarousel(gotoUrl,picUrl, type,typeName, title);
        md.put("count", count);
        return md;
    }

    /** 修改模块专区 */
    @ResponseBody
    @PostMapping("/updatePrefecture")
    public ModelMap updatePrefecture(@RequestParam(value = "id", required = true)String id,
                                   @RequestParam(value = "title", required = true)String title,
                                   String picUrl) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.updatePrefecture(id,title,picUrl);
        md.put("count", count);
        return md;
    }


    /** 修改公众号图片 */
    @ResponseBody
    @PostMapping("/updateQrCode")
    public ModelMap updateQrCode(@RequestParam(value = "id", required = true)String id,
                                 @RequestParam(value = "picUrl", required = true) String picUrl) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.updateQrCode(id,picUrl);
        md.put("count", count);
        return md;
    }

    /** headerInfo修改状态 */
    @ResponseBody
    @PostMapping("/headerInfoStatus")
    public ModelMap headerInfoStatus(@RequestParam(value = "id", required = true)String id) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.headerInfoStatus(id);
        md.put("count", count);
        return md;
    }

    /** 修改info */
    @ResponseBody
    @PostMapping("/updateInfo")
    public ModelMap updateInfo(@RequestParam(value = "id", required = true)String id,
                                            @RequestParam(value = "title", required = true)String title,
                                            @RequestParam(value = "picTitle", required = true)String picTitle,
                                            @RequestParam(value = "content", required = true)String content, HttpSession session1) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.updateInfo(id,title,picTitle,content,(String)session1.getAttribute("operNo"),(String)session1.getAttribute("operName"));
        md.put("count", count);
        return md;
    }

    /**
     *  集团动态的添加或保存
     */
    @ResponseBody
    @PostMapping("saveOrUpdate")
    public ModelMap saveOrUpdate(@RequestParam(value = "id")String id,
                                 @RequestParam(value = "title", required = true)String title,
                                 @RequestParam(value = "picTitle", required = true)String picTitle,
                                 @RequestParam(value = "content", required = true)String content,int isValid, HttpSession session1) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.update(id,title,picTitle,content,isValid, (String)session1.getAttribute("operNo"),(String)session1.getAttribute("operName"));
        md.put("count", count);
        return md;
    }

    /**
     *  企业荣誉的添加或保存
     */
    @ResponseBody
    @PostMapping("saveOrUpdate1")
    public ModelMap saveOrUpdate1(@RequestParam(value = "id")String id,
                                  @RequestParam(value = "title")String title,
                                  @RequestParam(value = "picTitle", required = true)String picTitle, HttpSession session1) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.update1(id,title,picTitle,(String)session1.getAttribute("operNo"),(String)session1.getAttribute("operName"));
        md.put("count", count);
        return md;
    }

    /**
     *  居然之家热门活动的添加或保存
     */
    @ResponseBody
    @PostMapping("saveOrUpdate2")
    public ModelMap saveOrUpdate2(@RequestParam(value = "id")String id,
                                  @RequestParam(value = "title", required = true)String title,
                                  @RequestParam(value = "picTitle", required = true)String picTitle,
                                  @RequestParam(value = "content", required = true)String content,int isValid, HttpSession session1) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.update2(id,title,picTitle,content,isValid, (String)session1.getAttribute("operNo"),(String)session1.getAttribute("operName"));
        md.put("count", count);
        return md;
    }

    /**
     *  联盟国际热门活动的添加或保存
     */
    @ResponseBody
    @PostMapping("saveOrUpdate3")
    public ModelMap saveOrUpdate3(@RequestParam(value = "id")String id,
                                  @RequestParam(value = "title", required = true)String title,
                                  @RequestParam(value = "picTitle", required = true)String picTitle,
                                  @RequestParam(value = "content", required = true)String content,int isValid, HttpSession session1) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.update3(id,title,picTitle,content,isValid, (String)session1.getAttribute("operNo"),(String)session1.getAttribute("operName"));
        md.put("count", count);
        return md;
    }

    /**
     *  联盟国际热门活动的添加或保存1
     */
    @ResponseBody
    @PostMapping("saveOrUpdate4")
    public ModelMap saveOrUpdate4(@RequestParam(value = "id")String id,
                                  @RequestParam(value = "title", required = true)String title,
                                  @RequestParam(value = "picTitle", required = true)String picTitle,
                                  @RequestParam(value = "content", required = true)String content,
                                  @RequestParam(value = "tagName", required = true)String tagName,
                                  @RequestParam(value = "startTime", required = true)String startTime,
                                  @RequestParam(value = "endTime", required = true)String endTime,int isValid, HttpSession session1) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.update4(id,title,picTitle,content,isValid,tagName,startTime, endTime,(String)session1.getAttribute("operNo"),(String)session1.getAttribute("operName"));
        md.put("count", count);
        return md;
    }

    /**
     *  投放点添加或保存
     */
    @ResponseBody
    @PostMapping("saveOrUpdate5")
    public ModelMap saveOrUpdate5(@RequestParam(value = "id")String id,
                                  @RequestParam(value = "title", required = true)String title) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.update5(id,title);
        md.put("count", count);
        return md;
    }


    // 集团动态添加修改跳转页
    @GetMapping("/queryById")
    public ModelAndView update(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if(req.getParameter("id")!= null && !req.getParameter("id").equals("") && !req.getParameter("id").equals("undefined")){
            Map<String, Object> m = webSettingService.queryById(req.getParameter("id"));
            if(m==null){
            }else{
                m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
            }
            mav.addObject("info", m);
        }
        mav.setViewName("/manage/info_update");
        return mav;
    }

    // 企业荣誉添加修改跳转页
    @GetMapping("/queryById1")
    public ModelAndView update1(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if(req.getParameter("id")!= null && !req.getParameter("id").equals("") && !req.getParameter("id").equals("undefined")){
            Map<String, Object> m = webSettingService.queryById(req.getParameter("id"));
            if(m==null){
            }else{
                m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
            }
            mav.addObject("info", m);
        }
        mav.setViewName("/manage/info_update1");
        return mav;
    }

    // 居然之家热门活动添加修改跳转页
    @GetMapping("/queryById2")
    public ModelAndView update2(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if(req.getParameter("id")!= null && !req.getParameter("id").equals("") && !req.getParameter("id").equals("undefined")){
            Map<String, Object> m = webSettingService.queryById(req.getParameter("id"));
            if(m==null){
            }else{
                m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
            }
            mav.addObject("info", m);
        }
        mav.setViewName("/manage/info_update2");
        return mav;
    }

    // 联盟国际热门活动添加修改跳转页
    @GetMapping("/queryById3")
    public ModelAndView update3(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if(req.getParameter("id")!= null && !req.getParameter("id").equals("") && !req.getParameter("id").equals("undefined")){
            Map<String, Object> m = webSettingService.queryById(req.getParameter("id"));
            if(m==null){
            }else{
                m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
            }
            mav.addObject("info", m);
        }
        mav.setViewName("/manage/info_update3");
        return mav;
    }

    // 联盟国际热门活动添加修改跳转页1
    @GetMapping("/queryById4")
    public ModelAndView update4(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if(req.getParameter("id")!= null && !req.getParameter("id").equals("") && !req.getParameter("id").equals("undefined")){
            Map<String, Object> m = webSettingService.queryCampaignById(req.getParameter("id"));
            if(m==null){
            }else{
                m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
            }
            mav.addObject("info", m);
        }
        mav.setViewName("/manage/info_update4");
        return mav;
    }

    // 投放点添加修改跳转页1
    @GetMapping("/queryById5")
    public ModelAndView update5(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        if(req.getParameter("id")!= null && !req.getParameter("id").equals("") && !req.getParameter("id").equals("undefined")){
            Map<String, Object> m = webSettingService.queryPointId(req.getParameter("id"));
            mav.addObject("info",m);
        }
        mav.setViewName("/manage/info_update5");
        return mav;
    }

    // 活动二维码
    @GetMapping("/queryQrCode")
    public ModelAndView queryQrCode(@RequestParam(value = "id", required = true)String id) {
        ModelAndView mav = new ModelAndView("/manage/activity_qrcode");
        mav.addObject("id", id);
        return mav;
    }

    /** 活动二维码list */
    @ResponseBody
    @GetMapping("/queryQrCodeList")
    public Map<String, Object> queryQrCodeList(@RequestParam("pageNumber")int pageNumber,
                                         @RequestParam("pageSize")int pageSize,
                                         @RequestParam(value = "id", required = true)String id) {
        Map<String, Object> m = new HashMap<>();
        List<Map<String, Object>> iList = webSettingService.queryByPointList(pageNumber, pageSize,id);
        if(iList==null){
        }else{
            for(int i =0;i < iList.size(); i++){
                iList.get(i).put("campaignId",id);
            }
        }
        m.put("rows", iList);
        m.put("total", webSettingService.queryByPointCount());
        return m;
    }

    // 投放点
    @GetMapping("/queryPoint")
    public ModelAndView queryPoint() {
        ModelAndView mav = new ModelAndView("/manage/point");
        return mav;
    }

    /** 投放点list */
    @ResponseBody
    @GetMapping("/queryPointList")
    public Map<String, Object> queryPointList(@RequestParam("pageNumber")int pageNumber,
                                               @RequestParam("pageSize")int pageSize) {
        Map<String, Object> m = new HashMap<>();
        List<Map<String, Object>> iList = webSettingService.queryPointList(pageNumber, pageSize);
        m.put("rows", iList);
        m.put("total", webSettingService.queryPointCount());
        return m;
    }

    /** 集团介绍 */
    @GetMapping("/groupIntroduction")
    public ModelAndView groupIntroductionPage() {
        ModelAndView mv = new ModelAndView("manage/info");
        Map<String, Object> m = webSettingService.queryTextareaInfo(0);
        if(m==null){
        }else{
            m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
        }
        mv.addObject("info", m);
        return mv;
    }

    /** 集团动态 */
    @GetMapping("/groupDynamic")
    public ModelAndView groupDynamicPage() {
        ModelAndView mav =new ModelAndView("manage/table_info");
        mav.addObject("infoType", 2);
        return mav;
    }

    /** 集团动态list */
    @ResponseBody
    @GetMapping("/infoList")
    public Map<String, Object> groupDynamicList(@RequestParam("pageNumber")int pageNumber,
                                                @RequestParam("pageSize")int pageSize) {
        Map<String, Object> m = new HashMap<>();
        List<Map<String, Object>> iList = webSettingService.queryByInfoList(pageNumber, pageSize, "2");
        if(iList ==null){
        }else {
            for (int i = 0; i < iList.size(); i++) {
                iList.get(i).put("showPicTitle", brand_imgPath + iList.get(i).get("picTitle"));
            }
        }
        m.put("rows", iList);
        m.put("total", webSettingService.queryByInfoCount("2"));
        return m;
    }

    /** 集团动态置顶 */
    @ResponseBody
    @GetMapping("/stick")
    public ModelMap stick(@RequestParam(value = "id", required = true)Integer id) {
        ModelMap md = new ModelMap();
        Integer count = webSettingService.stick(id);
        md.put("count", count);
        return md;
    }

    /** 企业文化 */
    @GetMapping("/enterpriseCulture")
    public ModelAndView enterpriseCulturePage() {
        ModelAndView mv = new ModelAndView("manage/info");
        Map<String, Object> m = webSettingService.queryTextareaInfo(3);
        if(m==null){
        }else{
            m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
        }
        mv.addObject("info", m);
        return mv;
    }

    /** 企业荣誉 */
    @GetMapping("/enterpriseHonor")
    public ModelAndView enterpriseHonor() {
        ModelAndView mav =new ModelAndView("manage/honor_info");
        return mav;
    }

    /** 企业荣誉list */
    @ResponseBody
    @GetMapping("/honorList")
    public Map<String, Object> honorList(@RequestParam("pageNumber")int pageNumber,
                                         @RequestParam("pageSize")int pageSize) {
        Map<String, Object> m = new HashMap<>();
        List<Map<String, Object>> iList = webSettingService.queryByInfoList(pageNumber, pageSize, "4");
        if(iList==null){
        }else{
            for(int i =0;i < iList.size(); i++){
                iList.get(i).put("showPicTitle", brand_imgPath+iList.get(i).get("picTitle"));
            }
        }
        m.put("rows", iList);
        m.put("total", webSettingService.queryByInfoCount("4"));
        return m;
    }

    /** 商务百问 */
    @GetMapping("/businessEdu")
    public ModelAndView businessEduPage() {
        ModelAndView mv = new ModelAndView("manage/info");
        Map<String, Object> m = webSettingService.queryTextareaInfo(5);
        if(m==null){
        }else{
            m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
        }
        mv.addObject("info", m);
        return mv;
    }

    /** 业态划分 */
    @GetMapping("/formatsDivision")
    public ModelAndView formatsDivisionPage() {
        ModelAndView mv = new ModelAndView("manage/info");
        Map<String, Object> m = webSettingService.queryTextareaInfo(6);
        if(m==null){
        }else{
            m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
        }
        mv.addObject("info", m);
        return mv;
    }

    /** 商务咨询 */
    @GetMapping("/businessConsulting")
    public ModelAndView businessConsultingPage() {
        ModelAndView mv = new ModelAndView("manage/info");
        Map<String, Object> m = webSettingService.queryTextareaInfo(7);
        if(m==null){
        }else{
            m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
        }
        mv.addObject("info", m);
        return mv;
    }

    /** 招聘职位 */
    @GetMapping("/inviteJob")
    public ModelAndView inviteJobPage() {
        ModelAndView mv = new ModelAndView("manage/info");
        Map<String, Object> m = webSettingService.queryTextareaInfo(9);
        if(m==null){
        }else{
            m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
        }
        mv.addObject("info", m);
        return mv;
    }

    /** 联系我们设置 */
    @GetMapping("/contactUs")
    public ModelAndView contactUsPage() {
        ModelAndView mv = new ModelAndView("manage/info");
        Map<String, Object> m = webSettingService.queryTextareaInfo(10);
        if(m==null){
        }else{
            m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
        }
        mv.addObject("info", m);
        return mv;
    }

    /** 火热销售 */
    @GetMapping("/sell")
    public ModelAndView sell() {
        ModelAndView mv = new ModelAndView("manage/info");
        Map<String, Object> m = webSettingService.queryTextareaInfo(11);
        if(m==null){
        }else{
            m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
        }
        mv.addObject("info", m);
        return mv;
    }

    /**
     * 联盟国际热门活动
     */
    @GetMapping("/activity1")
    public ModelAndView activity1() {
        ModelAndView mav =new ModelAndView("manage/activity_info1");
        return mav;
    }

    /** 联盟国际热门活动list */
    @ResponseBody
    @GetMapping("/activityList1")
    public Map<String, Object> activityList1(@RequestParam("pageNumber")int pageNumber,
                                            @RequestParam("pageSize")int pageSize) {
        Map<String, Object> m = new HashMap<>();
        List<Map<String, Object>> iList = webSettingService.queryByInfoList(pageNumber, pageSize, "14");
        if(iList==null){
        }else{
            for(int i =0;i < iList.size(); i++){
                iList.get(i).put("showPicTitle", brand_imgPath+iList.get(i).get("picTitle"));
            }
        }
        m.put("rows", iList);
        m.put("total", webSettingService.queryByInfoCount("14"));
        return m;
    }

    /**
     * 联盟国际热门活动2
     */
    @GetMapping("/activity2")
    public ModelAndView activity2() {
        ModelAndView mav =new ModelAndView("manage/activity_info2");
        return mav;
    }

    /** 联盟国际热门活动list */
    @ResponseBody
    @GetMapping("/activityList2")
    public Map<String, Object> activityList2(@RequestParam("pageNumber")int pageNumber,
                                             @RequestParam("pageSize")int pageSize) {
        Map<String, Object> m = new HashMap<>();
        List<Map<String, Object>> iList = webSettingService.queryByCampaign(pageNumber, pageSize);
        if(iList==null){
        }else{
            for(int i =0;i < iList.size(); i++){
                iList.get(i).put("showPicTitle", brand_imgPath+iList.get(i).get("picTitle"));
                iList.get(i).put("status",DateSizeUtil.isBelong((Date)iList.get(i).get("beginDate"), (Date)iList.get(i).get("finishDate")));
            }
        }
        m.put("rows", iList);
        m.put("total", webSettingService.queryByByCampaignCount());
        return m;
    }

    /**
     * 居然之家热门活动
     */
    @GetMapping("/activity")
    public ModelAndView activity() {
        ModelAndView mav =new ModelAndView("manage/activity_info");
        return mav;
    }

    /** 居然之家热门活动list */
    @ResponseBody
    @GetMapping("/activityList")
    public Map<String, Object> activityList(@RequestParam("pageNumber")int pageNumber,
                                         @RequestParam("pageSize")int pageSize) {
        Map<String, Object> m = new HashMap<>();
        List<Map<String, Object>> iList = webSettingService.queryByInfoList(pageNumber, pageSize, "12");
        if(iList==null){
        }else{
            for(int i =0;i < iList.size(); i++){
                iList.get(i).put("showPicTitle", brand_imgPath+iList.get(i).get("picTitle"));
            }
        }
        m.put("rows", iList);
        m.put("total", webSettingService.queryByInfoCount("12"));
        return m;
    }

    /**
     * 居然之家联系我们
     */
    @GetMapping("/actually")
    public ModelAndView actually() {
        ModelAndView mv = new ModelAndView("manage/info");
        Map<String, Object> m = webSettingService.queryTextareaInfo(13);
        if(m==null){
        }else{
            m.put("showPicTitle",brand_imgPath+m.get("picTitle"));
        }
        mv.addObject("info", m);
        return mv;
    }

    /** 发布 */
    @ResponseBody
    @PostMapping("/release")
    public Map<String, Object> release(@RequestParam(value = "status", required = true)String status,
                                       @RequestParam(value = "id", required = true)String id) {
        Map<String, Object> m = new HashMap<>();
        m.put("count", webSettingService.release(id, status));
        return m;
    }
    /** 活动发布 */
    @ResponseBody
    @PostMapping("/release1")
    public Map<String, Object> release1(@RequestParam(value = "status", required = true)String status,
                                       @RequestParam(value = "id", required = true)String id) {
        Map<String, Object> m = new HashMap<>();
        m.put("count", webSettingService.release1(id, status));
        return m;
    }
    /** 投放点发布 */
    @ResponseBody
    @PostMapping("/release2")
    public Map<String, Object> release2(@RequestParam(value = "status", required = true)String status,
                                        @RequestParam(value = "id", required = true)String id) {
        Map<String, Object> m = new HashMap<>();
        m.put("count", webSettingService.release2(id, status));
        return m;
    }

    /** 详情页中显示登记入口 */
    @ResponseBody
    @PostMapping("/checkInlet")
    public Map<String, Object> checkInlet(@RequestParam(value = "status", required = true)String status,
                                        @RequestParam(value = "id", required = true)String id) {
        Map<String, Object> m = new HashMap<>();
        m.put("count", webSettingService.release3(id, status));
        return m;
    }

    /**
     * 统计分析
     */
    @GetMapping("/activityCount")
    public ModelAndView activityCount() {
        ModelAndView mav =new ModelAndView("manage/activity_count");
        return mav;
    }

    /** 统计分析list */
    @ResponseBody
    @GetMapping("/activityCountList")
    public Map<String, Object> activityCountList(Integer pageNumber,
                                                 Integer pageSize,
                                                 @RequestParam("userName")String userName,
                                                 @RequestParam("startTime")String startTime,
                                                 @RequestParam("endTime")String endTime) {
        Map<String, Object> m = new HashMap<>();
        List<Map<String, Object>> iList = webSettingService.queryActivityCountList(pageNumber, pageSize, userName, startTime, endTime);
        m.put("rows", iList);
        m.put("total", webSettingService.queryActivityCountCount(userName, startTime, endTime));
        return m;
    }

    // 统计分析中的详情
    @GetMapping("/queryByActivityId")
    public ModelAndView queryByActivityId(@RequestParam(value = "id", required = true)String id) {
        ModelAndView mav = new ModelAndView("/manage/activity_count_detail");
        mav.addObject("id", id);
        return mav;
    }

    /**统计分析中的详情list */
    @ResponseBody
    @GetMapping("/queryByActivityIdList")
    public Map<String, Object> queryByActivityIdList(Integer pageNumber,
                                                 Integer pageSize,
                                                 @RequestParam(value = "id", required = true)String id) {
        Map<String, Object> m = new HashMap<>();
        List<Map<String, Object>> iList = webSettingService.queryPointByCamId(id);
        m.put("rows", iList);
        m.put("total", webSettingService.queryPointCountByCamId(id));
        return m;
    }

    /**统计分析中的详情list选中行后显示的表格 */
    @ResponseBody
    @GetMapping("/queryByOnclick")
    public Map<String, Object> queryByOnclick(
                                                     @RequestParam(value = "releasePointId", required = true)String releasePointId,
                                                     @RequestParam(value = "campaignId", required = true)String campaignId){
        Map<String, Object> m = new HashMap<>();
        List<Map<String, Object>> iList = webSettingService.queryByOnclick(releasePointId, campaignId);
        m.put("rows", iList);
        //m.put("total", webSettingService.queryByOnclickCount(id));
        return m;
    }

    /** 投放 */
    @ResponseBody
    @PostMapping("/putActivity")
    public Map<String, Object> putActivity(@RequestParam(value = "pointId", required = true)String pointId,
                                           @RequestParam(value = "camId", required = true)String camId) {
        Map<String, Object> m = new HashMap<>();
        m.put("count", webSettingService.putActivity(pointId,camId));
        return m;
    }

}
